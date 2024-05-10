package com.example.moodmarker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodmarker.moodEntries.EmotionType
//import java.sql.Time
import java.util.Date

@Entity(tableName = "moodmarkers")
data class MoodMarker(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "emotionType")
    val emotionType : EmotionType,

    @ColumnInfo(name = "dailyEntry")
    val dailyEntry : String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite : Boolean,

    @ColumnInfo(name = "date")
    val date: String = Date().toString(),

    @ColumnInfo(name = "imageURI")
    val imageURI: String? = null
) {

}