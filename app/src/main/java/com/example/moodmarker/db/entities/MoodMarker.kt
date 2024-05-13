package com.example.moodmarker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodmarker.moodEntries.EmotionType
//import java.sql.Time
import java.util.Date
//Database entity and sets table name to be called "moodmarkers'
@Entity(tableName = "moodmarkers")
data class MoodMarker(
    //ID is the primary key and auto-generated to enforce the uniqueness
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    // Column info for the emotionType field in the database table
    @ColumnInfo(name = "emotionType")
    val emotionType : EmotionType,
    // Column info for the dailyEntry field in the database table
    @ColumnInfo(name = "dailyEntry")
    val dailyEntry : String,
    // Column info for the isFavorite field in the database table
    @ColumnInfo(name = "isFavorite")
    val isFavorite : Boolean,
    // Column info for the date field in the database table with default value as the current date
    @ColumnInfo(name = "date")
    val date: String = Date().toString(),
    // Column info for the imageURI field in the database table
    @ColumnInfo(name = "imageURI")
    val imageURI: String? = null
) {

}