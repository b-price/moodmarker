package com.example.moodmarker.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodmarker.account.AccountsViewModel
import com.example.moodmarker.account.ChangePassword
import com.example.moodmarker.account.CreateAccount
import com.example.moodmarker.account.EditProfile
import com.example.moodmarker.account.LoginPage
import com.example.moodmarker.account.ProfilePage
import com.example.moodmarker.account.Settings
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.moodEntries.Entries
import com.example.moodmarker.moodEntries.EntriesViewModel
import com.example.moodmarker.moodEntries.MarkMyMood
import com.example.moodmarker.moodEntries.MoodCard

/** Navigation for the app. Creates the viewmodels and passes needed data to screen components -Ben **/
@Composable
fun MoodNavGraph(navController: NavHostController = rememberNavController(), paddingValues: PaddingValues) {
    val vm: EntriesViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val vmAccounts: AccountsViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    var enteredUsername = rememberSaveable { mutableStateOf("") }
    val users by vmAccounts.userList

    NavHost(
        navController = navController,
        startDestination = Routes.LoginPage.route,
        modifier = Modifier.padding(paddingValues)
    ){
        composable(Routes.LoginPage.route){
            LoginPage(
                users = users,
                nav = navController,
                enteredUsername = enteredUsername,
                enteredUser = vmAccounts.getEnteredUser(),
            )
        }

        composable(Routes.CreateAccount.route){
            CreateAccount(
                users = users,
                nav = navController,
                vmAccounts,
                enteredUser = vmAccounts.getEnteredUser()
                )
        }

        composable(Routes.MainPage.route){
            MarkMyMood(navController, vm::setPresetMoodMarker)
        }

        composable(Routes.AddMoodMarker.route){
            MoodCard(
                nav = navController, vm,
                presetMood = vm.getPresetMoodMarker(),
                isEdit = vm::getIsEdit,
                onEdit = vm::setIsEdit
            )
        }

        composable(Routes.Favorites.route){
            val showDialog by vm.showDialog
            Entries(
                entries = vm.getFavorites(),
                showDialog = showDialog,
                onDelete = vm::deleteMoodMarker,
                onPrepareDelete = vm::prepareDelete,
                dismissDialog = vm::dismissDialog,
                onToggleFavorite = vm::toggleFavorite,
                onEditMoodMarker = vm::setPresetMoodMarker,
                nav = navController,
                setEdit = vm::setIsEdit
            )
        }

        composable(Routes.Entries.route){
            val entries by vm.moodMarkerList
            val showDialog by vm.showDialog
            Entries(
                entries = entries,
                showDialog = showDialog,
                onDelete = vm::deleteMoodMarker,
                onPrepareDelete = vm::prepareDelete,
                dismissDialog = vm::dismissDialog,
                onToggleFavorite = vm::toggleFavorite,
                onEditMoodMarker = vm::setPresetMoodMarker,
                nav = navController,
                setEdit = vm::setIsEdit
            )
        }

        composable(Routes.Settings.route){
            Settings(navController)
        }

        composable(Routes.ProfilePage.route){
            ProfilePage(
                users = users,
                nav = navController,
                enteredUsername = enteredUsername,
                enteredUser = vmAccounts.getEnteredUser()
                )

        }

        composable(Routes.ChangePassword.route){
            ChangePassword(
                users = users,
                nav = navController,
                vmAccounts,
                enteredUser = vmAccounts.getEnteredUser()
            )
        }

        composable(Routes.EditProfile.route){
            EditProfile(
                users = users,
                nav = navController,
                vmAccounts,
                enteredUser = vmAccounts.getEnteredUser()
            )
        }

    }

}

