package com.example.moodmarker.db

import com.example.moodmarker.db.entities.MoodMarker

interface IMoodMarkerRepository {
    suspend fun getMoodMarkers() : List<MoodMarker>

    suspend fun deleteMoodMarker(moodMarker: MoodMarker)

    suspend fun addMoodMarker(moodMarker: MoodMarker)

    suspend fun updateMoodMarker(moodMarker: MoodMarker)

}