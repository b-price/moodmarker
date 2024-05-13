package com.example.moodmarker.moodEntries

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.quotes.QuoteViewModel
import com.example.moodmarker.uriToBitmap
import java.util.Date
import kotlin.reflect.KFunction0

@Composable
fun MoodCard(
    nav: NavHostController,
    vm: EntriesViewModel,
    presetMood: MoodMarker,
    isEdit: () -> Boolean,
    onEdit: KFunction0<Unit>
) {
    val quoteVm: QuoteViewModel = viewModel()
    val moodMarker = remember { mutableStateOf(presetMood) }
    // Retrieve the current context using LocalContext.current
    val context = LocalContext.current
    var imageURI by remember { mutableStateOf<android.net.Uri?>(null) }
    // Create a remembered variable to store the loaded image bitmap
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    // Create a remembered variable to track whether an image is loaded
    var isImageLoaded by remember { mutableStateOf(false) }

    if (presetMood.imageURI != null){
        imageURI = Uri.parse(presetMood.imageURI)
        val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
        context.contentResolver.takePersistableUriPermission(imageURI!!, flag)
        imageBitmap = uriToBitmap(context, imageURI!!)?.asImageBitmap()
        isImageLoaded = true
    }
//TODO: Cannot change image when in edited entry, only initial entry
    // Create an activity result launcher for picking visual media (images in this case)
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                // Grant read URI permission to access the selected URI
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, flag)

                // Convert the URI to a Bitmap and set it as the imageBitmap
                imageBitmap = uriToBitmap(context, it)?.asImageBitmap()
                imageURI = uri
                // Set isImageLoaded to true
                isImageLoaded = true
                moodMarker.value = moodMarker.value.copy(imageURI = imageURI.toString())
            }
        }

    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
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
                Icon(
                    Icons.Default.AccountBox,
                    "Add Image",
                    modifier = Modifier
                        .clickable {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                        .size(40.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
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
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
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
                // Check if an image is loaded
                if (isImageLoaded) {
                    // Display the loaded image using the Image composable
                    imageBitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                    }
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
                            vm.setPresetMoodMarker(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                        } else {
                            vm.addMoodMarker(moodMarker.value)
                        }

                        // Display notification with the submission confirmation
                        vm.displayNotification()

                        nav.popBackStack()
                    })
                    {
                        Text("Submit", fontSize = 7.em, modifier = Modifier.padding(4.dp))
                    }
                }

            }

        }
    }
}

enum class EmotionType {
    Angry, Sad, Neutral, Happy, Excited
}