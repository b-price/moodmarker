package com.example.moodmarker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController

@Composable
fun MarkMyMood(nav: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.height(50.dp))
        }
        Row () {
            Text("😐",
                fontSize = 18.em,
                modifier = Modifier.clickable {
                    nav.navigate(Routes.AddMoodMarker.route + "/Neutral")
                }
            )
            Text("🙂", fontSize = 18.em, modifier = Modifier.clickable {
                nav.navigate(Routes.AddMoodMarker.route + "/Happy")
            })
        }
        Row {
            Text("😡", fontSize = 18.em, modifier = Modifier.clickable {
                nav.navigate(Routes.AddMoodMarker.route + "/Angry")
            })
            Text("🙁", fontSize = 18.em, modifier = Modifier.clickable {
                nav.navigate(Routes.AddMoodMarker.route + "/Sad")
            })
            Text("😁", fontSize = 18.em, modifier = Modifier.clickable {
                nav.navigate(Routes.AddMoodMarker.route + "/Excited")
            })
        }
        Row (modifier = Modifier.padding(50.dp)){
            Button(onClick = { nav.navigate(Routes.AddMoodMarker.route + "/Happy") }) {
                Text("Mark My Mood!", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }
        Row {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}