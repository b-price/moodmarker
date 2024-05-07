package com.example.moodmarker

import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlin.reflect.KFunction0

@Composable
fun MoodCard(
    nav: NavHostController,
    vm: EntriesViewModel,
    presetMood: MoodMarker,
    isEdit: () -> Boolean,
    onEdit: KFunction0<Unit>
) {
    val moodMarker = remember { mutableStateOf(presetMood) }

    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Column {



        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                if (moodMarker.value.emotionType == EmotionType.Angry) {
                    Text("\uD83D\uDE21", fontSize = 18.em)
                } else if (moodMarker.value.emotionType == EmotionType.Sad) {
                    Text("\uD83D\uDE41", fontSize = 18.em)
                } else if (moodMarker.value.emotionType == EmotionType.Neutral) {
                    Text("\uD83D\uDE10", fontSize = 18.em)
                } else if (moodMarker.value.emotionType == EmotionType.Happy) {
                    Text("\uD83D\uDE42", fontSize = 18.em)
                } else {
                    Text("\uD83D\uDE01", fontSize = 18.em)
                }
                if (moodMarker.value.isFavorite){
                    Icon(
                        Icons.Default.Favorite,
                        "Favorite",
                        modifier = Modifier
                            .clickable {
                                moodMarker.value = moodMarker.value.copy(isFavorite = false)
                            }
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.primary)
                } else {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        "Not Favorite",
                        modifier = Modifier
                            .clickable {
                                moodMarker.value = moodMarker.value.copy(isFavorite = true)
                            }
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.primary)
                }
            }

        }
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("\uD83D\uDE21", fontSize = 8.em)
                Text("\uD83D\uDE41", fontSize = 8.em)
                Text("\uD83D\uDE10", fontSize = 8.em)
                Text("\uD83D\uDE42", fontSize = 8.em)
                Text("\uD83D\uDE01", fontSize = 8.em)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Angry,
                    onClick = {
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Angry)
                    }
                )
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Sad,
                    onClick = {
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Sad)
                    }
                )
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Neutral,
                    onClick = {
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Neutral)
                    }
                )
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Happy,
                    onClick = {
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Happy)
                    }
                )
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Excited,
                    onClick = {
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Excited)
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextField(
                    value = moodMarker.value.dailyEntry,
                    onValueChange = {
                        moodMarker.value = moodMarker.value.copy(dailyEntry = it)
                    },
                    label = { Text("Enter your MoodMarker") }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (isEdit()){
                        vm.updateMoodMarker(moodMarker.value)
                        onEdit()
                        vm.setPresetMoodMarker(MoodMarker(0, EmotionType.Happy, "", false))
                    } else {
                        vm.addMoodMarker(moodMarker.value)
                    }
                    nav.popBackStack()
                })
                {
                    Text("Submit", fontSize = 7.em, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

//fun getImage(){
//    // Registers a photo picker activity launcher in single-select mode.
//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        // Callback is invoked after the user selects a media item or closes the
//        // photo picker.
//        if (uri != null) {
//            Log.d("PhotoPicker", "Selected URI: $uri")
//        } else {
//            Log.d("PhotoPicker", "No media selected")
//        }
//    }
//    // Launch the photo picker and let the user choose only images.
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//}
//
//fun registerForActivityResult(
//    pickVisualMedia: ActivityResultContracts.PickVisualMedia,
//    any: Any
//): Any {
//    TODO("Not yet implemented")
//}


enum class EmotionType {
    Angry, Sad, Neutral, Happy, Excited
}