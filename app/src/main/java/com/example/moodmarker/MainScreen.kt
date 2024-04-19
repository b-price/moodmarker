package com.example.moodmarker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    Scaffold (
        topBar = {TopBar()},
        bottomBar = { BottomBar(nav = nav)}
    ){pv: PaddingValues ->
        MoodNavGraph(navController = nav, pv)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(title = {
        Text(
            "MoodMarker",
            fontSize = 9.em,
            fontWeight = FontWeight.Bold
        )
    })
}

@Composable
private fun BottomBar(nav: NavHostController) {
    val currentBackStack by nav.currentBackStackEntryAsState()
    val currentRoute: String? = currentBackStack?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Routes.MainPage.route, 
            onClick = { nav.navigate(Routes.MainPage.route) },
            icon = { Icon(Icons.Default.Home, "Home")},
            label = { Text("Home")})
        NavigationBarItem(
            selected = currentRoute == Routes.Favorites.route,
            onClick = { nav.navigate(Routes.Favorites.route) },
            icon = { Icon(Icons.Default.Favorite, "Favorites") },
            label = { Text("Favorites")})
        NavigationBarItem(
            selected = currentRoute == Routes.Entries.route,
            onClick = { nav.navigate(Routes.Entries.route) },
            icon = { Icon(Icons.Default.List, "Entries") },
            label = { Text("Entries")})
        NavigationBarItem(
            selected = currentRoute == Routes.Settings.route,
            onClick = { nav.navigate(Routes.Settings.route) },
            icon = { Icon(Icons.Default.AccountCircle, "Settings") },
            label = { Text("Settings")})
    }
}