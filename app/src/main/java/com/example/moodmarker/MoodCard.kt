package com.example.moodmarker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController

@Composable
fun MoodCard(nav: NavHostController) {
    val originalMoodMarker = remember { mutableStateOf("") }
    val selectedEmotion = remember { mutableStateOf(EmotionType.Excited) }

    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Column {
            Spacer(modifier = Modifier.height(150.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (selectedEmotion.value == EmotionType.Angry) {
                Text("\uD83D\uDE21", fontSize = 18.em)
            } else if (selectedEmotion.value == EmotionType.Sad) {
                Text("\uD83D\uDE41", fontSize = 18.em)
            } else if (selectedEmotion.value == EmotionType.Neutral) {
                Text("\uD83D\uDE10", fontSize = 18.em)
            } else if (selectedEmotion.value == EmotionType.Happy) {
                Text("\uD83D\uDE42", fontSize = 18.em)
            } else {
                Text("\uD83D\uDE01", fontSize = 18.em)
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Row() {
                Text("\uD83D\uDE21", fontSize = 8.em)
                Text("\uD83D\uDE41", fontSize = 8.em)
                Text("\uD83D\uDE10", fontSize = 8.em)
                Text("\uD83D\uDE42", fontSize = 8.em)
                Text("\uD83D\uDE01", fontSize = 8.em)
            }
            Row() {
                RadioButton(selected = selectedEmotion.value == EmotionType.Angry,
                    onClick = {
                        selectedEmotion.value = EmotionType.Angry
                    }
                )
                RadioButton(selected = selectedEmotion.value == EmotionType.Sad,
                    onClick = {
                        selectedEmotion.value = EmotionType.Sad
                    }
                )
                RadioButton(selected = selectedEmotion.value == EmotionType.Neutral,
                    onClick = {
                        selectedEmotion.value = EmotionType.Neutral
                    }
                )
                RadioButton(selected = selectedEmotion.value == EmotionType.Happy,
                    onClick = {
                        selectedEmotion.value = EmotionType.Happy
                    }
                )
                RadioButton(selected = selectedEmotion.value == EmotionType.Excited,
                    onClick = {
                        selectedEmotion.value = EmotionType.Excited
                    }
                )
            }
            TextField(
                value = originalMoodMarker.value,
                onValueChange = { originalMoodMarker.value = it },
                label = { Text("Enter your MoodMarker") }
            )
        }
    }
}
enum class EmotionType {
    Angry, Sad, Neutral, Happy, Excited
}