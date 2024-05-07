package com.example.moodmarker

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

@Composable
fun MoodMarkerRow(
    moodMarker: MoodMarker,
    onPrepareDelete: (MoodMarker) -> Unit,
    onToggleFavorite: (MoodMarker) -> Unit,
    onEditMoodMarker: (MoodMarker) -> Unit
){
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
                Text(moodMarker.date.toString())
            }
            Column {
                if (moodMarker.isFavorite){
                    Icon(
                        Icons.Default.Favorite,
                        "Favorite",
                        modifier = Modifier.clickable { onToggleFavorite(moodMarker) }
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.primary)
                } else {
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
                text = when (moodMarker.emotionType) {
                    EmotionType.Angry -> "😡"
                    EmotionType.Sad -> "🙁"
                    EmotionType.Neutral -> "😐"
                    EmotionType.Happy -> "🙂"
                    EmotionType.Excited -> "😁"
                },
                fontSize = 15.em
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (moodMarker.dailyEntry.length < 70){
                Text(text = moodMarker.dailyEntry)
            } else {
                Text(text = moodMarker.dailyEntry.slice(0..70) + "...")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){ //TODO: Edit moodmarker
            Button(onClick = { onPrepareDelete(moodMarker) }) {
                Text("Delete Entry")
            }
            Button(onClick = { onEditMoodMarker(moodMarker) }) {
                Text("Edit Entry")
            }
        }
    }
}