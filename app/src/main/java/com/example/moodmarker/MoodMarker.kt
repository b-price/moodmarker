package com.example.moodmarker

import java.sql.Time
import java.util.Date

data class MoodMarker(
    val id: Int,
    val emotionType : EmotionType,
    val dailyEntry : String,
    val isFavorite : Boolean,
    val date: Date
) {
}