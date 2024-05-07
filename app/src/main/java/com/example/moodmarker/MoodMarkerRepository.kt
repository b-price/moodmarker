package com.example.moodmarker

import android.app.Application
import androidx.room.Room

class MoodMarkerRepository(app: Application) : IMoodMarkerRepository {
    //private var _entries = listOf<MoodMarker>()
    private val db: MoodMarkersDatabase
    private val sample = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam pulvinar tortor erat, nec dignissim arcu tincidunt ac. Morbi volutpat facilisis tortor, aliquet egestas enim. In non leo sapien. Nunc molestie mauris sit amet congue iaculis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas ut egestas odio. Nam a dui eu neque faucibus congue luctus nec arcu."

    //Makes 10 sample entries
    init {
        db = Room.databaseBuilder(app, MoodMarkersDatabase::class.java, "moodmarkers.db")
            .fallbackToDestructiveMigration()
            .build()
//        _entries = (0..10).map { i ->
//            MoodMarker(i, EmotionType.entries.random(), i.toString() + sample, true, Date())
//        }
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

//    override suspend fun getFavorites(): List<MoodMarker>{
//        return db.moodMarkersDao().getFavorites()
//    }
}