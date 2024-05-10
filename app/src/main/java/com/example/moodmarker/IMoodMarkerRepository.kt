package com.example.moodmarker

import com.example.moodmarker.entities.MoodMarker

interface IMoodMarkerRepository {
    suspend fun getMoodMarkers() : List<MoodMarker>

    suspend fun deleteMoodMarker(moodMarker: MoodMarker)

    suspend fun addMoodMarker(moodMarker: MoodMarker)

    suspend fun updateMoodMarker(moodMarker: MoodMarker)

}