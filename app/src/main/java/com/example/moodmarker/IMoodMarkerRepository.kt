package com.example.moodmarker

interface IMoodMarkerRepository {
    suspend fun getMoodMarkers() : List<MoodMarker>

    suspend fun deleteMoodMarker(moodMarker: MoodMarker)

    suspend fun addMoodMarker(moodMarker: MoodMarker)

    suspend fun updateMoodMarker(moodMarker: MoodMarker)

    suspend fun getFavorites() : List<MoodMarker>
}