package com.example.moodmarker.moodEntries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.MoodMarker

@Composable
fun MoodMarkerRow(
    moodMarker: MoodMarker,
    onPrepareDelete: (MoodMarker) -> Unit,
    onToggleFavorite: (MoodMarker) -> Unit,
    onEditMoodMarker: (MoodMarker) -> Unit,
    nav: NavHostController,
    setEdit: () -> Unit
){
    //Each MoodMarker will be in a card container with rounded corners and padding
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                //Displays the date and time
                Text(moodMarker.date)
            }
            Column {
                //Check if the moodMarker is marked as favorite
                if (moodMarker.isFavorite){
                    //If the moodMarker is marked as favorite show a filled heart icon
                    Icon(
                        Icons.Default.Favorite,
                        "Favorite",
                        modifier = Modifier.clickable { onToggleFavorite(moodMarker) }
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.primary)
                } else {
                    //If the moodMarker is not favorite show an outlined heart icon
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        "Not Favorite",
                        modifier = Modifier.clickable { onToggleFavorite(moodMarker) }
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                //Check the emotion type of the Mood Marker
                text = when (moodMarker.emotionType) {
                    EmotionType.Angry -> "😡"       //If angry show an angry face emoji
                    EmotionType.Sad -> "🙁"         //If sad show a sad face emoji
                    EmotionType.Neutral -> "😐"     //If neutral show a neutral face emoji
                    EmotionType.Happy -> "🙂"       //If happy show a happy face emoji
                    EmotionType.Excited -> "😁"     //If excited show an excited face emoji
                },
                fontSize = 15.em
            )
            Spacer(modifier = Modifier.width(16.dp))
            //If length of the entry is less than 70 display the entire entry
            if (moodMarker.dailyEntry.length < 70){
                Text(text = moodMarker.dailyEntry)
            } else {
                Text(text = moodMarker.dailyEntry.slice(0..70) + "...")   //If length is greater than or equal to 70 do not show the full entry
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //Delete entry button
            //When the delete entry button is clicked prepare to delete the entry dialog will appear
            Button(onClick = { onPrepareDelete(moodMarker) }) {
                Text("Delete Entry")
            }
            //Edit Entry button
            //When the button is clicked call function to edit the mood marker
            Button(onClick = {
                onEditMoodMarker(moodMarker)
                setEdit()                                 //set the edit mode
                nav.navigate("AddMoodMarker")       //Navigate to the AddMoodMarker destination
            }) {
                Text("Edit Entry")
            }
        }
    }
}