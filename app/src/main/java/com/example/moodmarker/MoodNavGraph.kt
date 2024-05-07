package com.example.moodmarker

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MoodNavGraph(navController: NavHostController = rememberNavController(), paddingValues: PaddingValues) {
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
            MarkMyMood(navController)
        }
        composable(
            Routes.AddMoodMarker.route + "/{presetMood}",
            arguments = listOf(navArgument("presetMood") { type = NavType.StringType }
            )){ backStackEntry ->
            MoodCard(navController, presetMood = backStackEntry.arguments?.getString("presetMood"))
        } //TODO: Fix favorites page
 /*       composable(Routes.Favorites.route){
            val vm: EntriesViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
            val showDialog by vm.showDialog
            Entries(
                entries = vm.getFavorites(),
                showDialog = showDialog,
                onDelete = vm::deleteMoodMarker,
                onPrepareDelete = vm::prepareDelete,
                dismissDialog = vm::dismissDialog)
        } */
        composable(Routes.Entries.route){
            val vm: EntriesViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
            val entries by vm.moodMarkerList
            val showDialog by vm.showDialog
            Entries(
                entries = entries,
                showDialog = showDialog,
                onDelete = vm::deleteMoodMarker,
                onPrepareDelete = vm::prepareDelete,
                dismissDialog = vm::dismissDialog)
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

