package com.example.moodmarker.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodmarker.EmotionType
//import java.sql.Time
import java.util.Date

@Entity(tableName = "moodmarkers")
data class MoodMarker(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val emotionType : EmotionType,
    val dailyEntry : String,
    val isFavorite : Boolean,
    val date: String = Date().toString(),
    val imageURI: String? = null
) {

}