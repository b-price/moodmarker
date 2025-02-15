package com.example.moodmarker.moodEntries

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moodmarker.quotes.QuoteViewModel
import com.example.moodmarker.navigation.Routes
import com.example.moodmarker.db.entities.MoodMarker
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
            //Emojis representing different emotions
            //When clicked it preselects the emoji and navigates to the mood card with the radio button preselected with the clicked emotion
            Text("😐",
                fontSize = 18.em,
                modifier = Modifier.clickable {
                    //Picks the neutral mood and navigates to the mood marker card
                    onPickMood(MoodMarker(0, EmotionType.Neutral, "", false, Date().toString()))
                    nav.navigate(Routes.AddMoodMarker.route)
                }
            )
            Text("🙂", fontSize = 18.em, modifier = Modifier.clickable {
                //Picks the happy mood and navigates to the mood marker card
                onPickMood(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
        }
        Row {
            Text("😡", fontSize = 18.em, modifier = Modifier.clickable {
                //Picks the angry mood and navigates to the mood marker card
                onPickMood(MoodMarker(0, EmotionType.Angry, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
            Text("🙁", fontSize = 18.em, modifier = Modifier.clickable {
                //Picks the sad mood and navigates to the mood marker card
                onPickMood(MoodMarker(0, EmotionType.Sad, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
            Text("😁", fontSize = 18.em, modifier = Modifier.clickable {
                //Picks the excited mood and navigates to the mood marker card
                onPickMood(MoodMarker(0, EmotionType.Excited, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            })
        }
        Row (modifier = Modifier.padding(50.dp)){
            //Button to mark the daily mood and navigate to the mood marker card
            Button(onClick = {
                //Default is the happy emotion with a blank daily entry
                onPickMood(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                nav.navigate(Routes.AddMoodMarker.route)
            }) {
                Text("Mark My Mood!", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }
        Row {
            Spacer(modifier = Modifier.height(100.dp))
        }
        //Displays a random quote from the zen quotes API
        //The quote is fetched from the Quote view
        Text(
            text = vm.quote.value ?: "Loading quote...",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )


    }
}