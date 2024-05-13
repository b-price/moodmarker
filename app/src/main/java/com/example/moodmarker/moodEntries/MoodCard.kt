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
    //Remember the current mood marker state
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
    //The MoodCard is inside a rounded corner card
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
                //Display an emoji based on the selected emotion type
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
                //Display the favorite icon based on the favorite status
                //If moodMarker is favorite show a filled in heart icon
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
                        Icons.Outlined.FavoriteBorder,      //if not favorite show an outlined heart shape
                        "Not Favorite",
                        modifier = Modifier
                            .clickable {
                                moodMarker.value = moodMarker.value.copy(isFavorite = true)  //Toggle favorite status
                            }
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.primary)
                }
                //Image icon button when clicked it will open the the photo gallery
                Icon(
                    Icons.Default.AccountBox,
                    "Add Image",
                    modifier = Modifier
                        .clickable {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) // Launch image Picker
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
                //Display the emojis in a smaller size
                Text("\uD83D\uDE21", fontSize = 8.em)
                Text("\uD83D\uDE41", fontSize = 8.em)
                Text("\uD83D\uDE10", fontSize = 8.em)
                Text("\uD83D\uDE42", fontSize = 8.em)
                Text("\uD83D\uDE01", fontSize = 8.em)
            }
            //Row of RadioButtons for selecting different emotions
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                //Radio Button for the Angry emotion
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Angry,
                    onClick = {
                        //When clicked set the emotion type to angry
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Angry)
                    }
                )
                //Radio Button for the Sad emotion
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Sad,
                    onClick = {
                        //When clicked set the emotion type to sad
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Sad)
                    }
                )
                //Radio Button for the Neutral emotion
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Neutral,
                    onClick = {
                        //When clicked set the emotion type to neutral
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Neutral)
                    }
                )
                //Radio Button for the Happy emotion
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Happy,
                    onClick = {
                        //When clicked set the emotion type to happy
                        moodMarker.value = moodMarker.value.copy(emotionType = EmotionType.Happy)
                    }
                )
                //Radio Button for the Excited emotion
                RadioButton(selected = moodMarker.value.emotionType == EmotionType.Excited,
                    onClick = {
                        //When clicked set the emotion type to excited
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
                    //TextField for writing the daily entry
                    TextField(
                        value = moodMarker.value.dailyEntry, // Set the value of TextField to daily entry text
                        onValueChange = {
                            moodMarker.value = moodMarker.value.copy(dailyEntry = it) /// Update daily entry text when changed
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
                        if (isEdit()){ //Check if it is in edit mode
                            vm.updateMoodMarker(moodMarker.value) // Update the mood marker in the view model
                            onEdit()   //Trigger the edit action
                            vm.setPresetMoodMarker(MoodMarker(0, EmotionType.Happy, "", false, Date().toString()))
                        } else {
                            vm.addMoodMarker(moodMarker.value) //add mood marker to the view model
                        }

                        // Display notification with the submission confirmation
                        vm.displayNotification()
                        //Takes user back to the home page
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

//Enum represents different types of emotions
enum class EmotionType {
    Angry, Sad, Neutral, Happy, Excited
}