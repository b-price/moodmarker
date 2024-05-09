package com.example.moodmarker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import java.util.Date

@Composable
fun MarkMyMood(nav: NavHostController, onPickMood: (MoodMarker) -> Unit) {
    val vm: QuoteViewModel = viewModel()
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
                    onPickMood(MoodMarker(0, EmotionType.Neutral, "", false, Date().toString()))
                    nav.navigate(Routes.AddMoodMarker.route)
                }
            )
            Text("🙂", fontSize = 18.em, modifier = Modifier.clickable {
                onPickMood(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
        }
        Row {
            Text("😡", fontSize = 18.em, modifier = Modifier.clickable {
                onPickMood(MoodMarker(0, EmotionType.Angry, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
            Text("🙁", fontSize = 18.em, modifier = Modifier.clickable {
                onPickMood(MoodMarker(0, EmotionType.Sad, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
            Text("😁", fontSize = 18.em, modifier = Modifier.clickable {
                onPickMood(MoodMarker(0, EmotionType.Excited, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
        }
        Row (modifier = Modifier.padding(50.dp)){
            Button(onClick = {
                onPickMood(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            }) {
                Text("Mark My Mood!", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }
        Row {
            Spacer(modifier = Modifier.height(100.dp))
        }
        Text(
            text = vm.quote.value?: "Loading quote...",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

    }
}