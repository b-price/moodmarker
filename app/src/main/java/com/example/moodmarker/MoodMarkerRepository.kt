package com.example.moodmarker

import java.util.Date

class MoodMarkerRepository {
    private var _entries = listOf<MoodMarker>()
    private val sample = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam pulvinar tortor erat, nec dignissim arcu tincidunt ac. Morbi volutpat facilisis tortor, aliquet egestas enim. In non leo sapien. Nunc molestie mauris sit amet congue iaculis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas ut egestas odio. Nam a dui eu neque faucibus congue luctus nec arcu."

//    init {
//        _entries = (0..10).map { i ->
//            MoodMarker(i, EmotionType.entries.random(), i.toString() + sample, true, Date())
//        }
//    }

    fun getMoodMarkers(): List<MoodMarker>{
        return _entries
    }

    fun deleteMoodMarker(entry: MoodMarker){
        _entries = _entries.filter { mm -> mm.id != entry.id }
    }

    fun addMoodMarker(entry: MoodMarker){
        _entries = listOf(entry) + _entries
    }

    fun updateMoodMarker(entry: MoodMarker){
        deleteMoodMarker(entry)
        addMoodMarker(entry)
    }

    fun getFavorites(): List<MoodMarker>{
        return _entries.filter { mm -> mm.isFavorite }
    }
}