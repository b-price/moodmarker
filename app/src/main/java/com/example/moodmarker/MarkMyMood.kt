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

@Composable
fun MarkMyMood() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.height(150.dp))
        }
        Row () {
            Text("😐", fontSize = 18.em, modifier = Modifier.clickable {
                /* TODO: Navigation to AddMoodMarker with preset mood */
            })
            Text("🙂", fontSize = 18.em, modifier = Modifier.clickable {
                /* TODO: Navigation to AddMoodMarker with preset mood */
            })
        }
        Row {
            Text("😡", fontSize = 18.em, modifier = Modifier.clickable {
                /* TODO: Navigation to AddMoodMarker with preset mood */
            })
            Text("🙁", fontSize = 18.em, modifier = Modifier.clickable {
                /* TODO: Navigation to AddMoodMarker with preset mood */
            })
            Text("😁", fontSize = 18.em, modifier = Modifier.clickable {
                /* TODO: Navigation to AddMoodMarker with preset mood */
            })
        }
        Row (modifier = Modifier.padding(50.dp)){
            Button(onClick = { /*TODO: Navigation to AddMoodMarker*/ }) {
                Text("Mark My Mood!", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }
        Row {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}