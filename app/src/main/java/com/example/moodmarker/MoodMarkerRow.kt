package com.example.moodmarker

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.em

@Composable
fun MoodMarkerRow(
    moodMarker: MoodMarker
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
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = when (moodMarker.emotionType) {
                    EmotionType.Angry -> "😡"
                    EmotionType.Sad -> "😢"
                    EmotionType.Neutral -> "😐"
                    EmotionType.Happy -> "😊"
                    EmotionType.Excited -> "😁"

                },
                fontSize = 18.em
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = moodMarker.dailyEntry)

        }
    }
}