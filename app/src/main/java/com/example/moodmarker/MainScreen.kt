package com.example.moodmarker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
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
    ){
        MoodNavGraph(navController = nav)
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
            selected = currentRoute == Routes.FavMoodMarkers.route,
            onClick = { nav.navigate(Routes.FavMoodMarkers.route) },
            icon = { Icon(Icons.Default.Favorite, "Favorites") },
            label = { Text("Favorites")})
        NavigationBarItem(
            selected = currentRoute == Routes.PastMoodMarkers.route,
            onClick = { nav.navigate(Routes.PastMoodMarkers.route) },
            icon = { Icon(Icons.Default.List, "Entries") },
            label = { Text("Entries")})
        NavigationBarItem(
            selected = currentRoute == Routes.Settings.route,
            onClick = { nav.navigate(Routes.Settings.route) },
            icon = { Icon(Icons.Default.AccountCircle, "Settings") },
            label = { Text("Settings")})
    }
}