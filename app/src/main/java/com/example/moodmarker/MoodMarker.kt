package com.example.moodmarker

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
//import java.sql.Time
import java.util.Date

@Entity(tableName = "moodmarkers")
data class MoodMarker(
    @PrimaryKey
    val id: Int,
    val emotionType : EmotionType,
    val dailyEntry : String,
    val isFavorite : Boolean,

) {
    @Ignore
    val date: Date = Date()
}