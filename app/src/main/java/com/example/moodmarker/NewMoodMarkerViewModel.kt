package com.example.moodmarker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moodmarker.entities.MoodMarker

class NewMoodMarkerViewModel: ViewModel() {
    private val _dailyEntry: MutableState<String> = mutableStateOf("")
    val dailyEntry: State<String> = _dailyEntry
    private val _emotionType: MutableState<EmotionType> = mutableStateOf(EmotionType.Excited)
    val emotionType: State<EmotionType> = _emotionType
    private val _moodMarkers: MutableState<List<MoodMarker>> = mutableStateOf(emptyList())
    val moodMarkers: State<List<MoodMarker>> = _moodMarkers

    fun setDailyEntry(dailyEntry: String) {
        _dailyEntry.value = dailyEntry
    }

    fun setEmotionType(emotionType: EmotionType) {
        _emotionType.value = emotionType
    }

    fun addMoodMarker(moodMarker: MoodMarker) {
        _moodMarkers.value = _moodMarkers.value + moodMarker
    }
}