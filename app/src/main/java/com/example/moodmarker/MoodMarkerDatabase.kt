package com.example.moodmarker

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface MoodMarkersDao {
    @Query("select * from moodmarkers")
    suspend fun getMoodMarkers(): List<MoodMarker>
    @Insert
    suspend fun addMoodMarker(moodMarker: MoodMarker)
    @Delete
    suspend fun deleteMoodMarker(moodMarker: MoodMarker)
    @Update
    suspend fun updateMoodMarker(moodMarker: MoodMarker)
}
@Database(entities = [MoodMarker::class], version = 3, exportSchema = true)
abstract class MoodMarkersDatabase : RoomDatabase() {
    abstract fun moodMarkersDao(): MoodMarkersDao
}