package com.example.moodmarker.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodmarker.account.CreateAccount
import com.example.moodmarker.account.LoginPage
import com.example.moodmarker.account.ProfilePage
import com.example.moodmarker.account.Settings
import com.example.moodmarker.moodEntries.Entries
import com.example.moodmarker.moodEntries.EntriesViewModel
import com.example.moodmarker.moodEntries.MarkMyMood
import com.example.moodmarker.moodEntries.MoodCard

@Composable
fun MoodNavGraph(navController: NavHostController = rememberNavController(), paddingValues: PaddingValues) {
    val vm: EntriesViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    NavHost(
        navController = navController,
        startDestination = Routes.LoginPage.route,
        modifier = Modifier.padding(paddingValues)
    ){
        composable(Routes.LoginPage.route){
            LoginPage(navController)
        }
        composable(Routes.CreateAccount.route){
            CreateAccount(navController)
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
            ProfilePage(navController)
        }
        composable(Routes.AppSettings.route){
            /* TODO: Settings */
        }
    }

}

