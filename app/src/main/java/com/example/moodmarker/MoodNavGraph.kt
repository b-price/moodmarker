package com.example.moodmarker

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MarkMyMood(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.MainPage.route){
        composable(Routes.MainPage.route){
            Row {

            }
            Row () {
                Text("😐", fontSize = 18.em)
                Text("🙂", fontSize = 18.em)
            }
            Row {
                Text("😡", fontSize = 18.em)
                Text("🙁", fontSize = 18.em)
                Text("😁", fontSize = 18.em)
            }
            Row (modifier = Modifier.padding(50.dp)){
                Button(onClick = { navController.navigate(Routes.AddMoodMarker.route) }) {
                    Text("Mark My Mood!", fontSize = 7.em, modifier = Modifier.padding(10.dp))
                }
            }
            Row {

            }
        }
    }

}