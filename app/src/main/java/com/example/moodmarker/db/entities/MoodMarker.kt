package com.example.moodmarker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodmarker.moodEntries.EmotionType
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