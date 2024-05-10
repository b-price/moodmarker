package com.example.moodmarker

import android.app.Application
import androidx.room.Room
import com.example.moodmarker.entities.MoodMarker

class MoodMarkerRepository(app: Application) : IMoodMarkerRepository {
    private val db: MoodMarkersDatabase
    init {
        db = Room.databaseBuilder(app, MoodMarkersDatabase::class.java, "moodmarkers.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override suspend fun getMoodMarkers(): List<MoodMarker> {
        return db.moodMarkersDao().getMoodMarkers()
    }

    override suspend fun deleteMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().deleteMoodMarker(moodMarker)
    }

    override suspend fun addMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().addMoodMarker(moodMarker)
    }

    override suspend fun updateMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().updateMoodMarker(moodMarker)
    }
}