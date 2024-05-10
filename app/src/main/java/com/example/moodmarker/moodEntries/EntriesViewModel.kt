package com.example.moodmarker.moodEntries

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.db.IMoodMarkerRepository
import com.example.moodmarker.db.MoodMarkerRepository
import com.example.moodmarker.db.entities.MoodMarker
import kotlinx.coroutines.launch
import java.util.Date

class EntriesViewModel(app: Application): AndroidViewModel(app) {
    private val _moodMarkerList: MutableState<List<MoodMarker>> = mutableStateOf(listOf())
    val moodMarkerList: State<List<MoodMarker>> = _moodMarkerList
    private var _entryToBeDeleted: MoodMarker?
    private val _showDialog: MutableState<Boolean>
    val showDialog: State<Boolean>
    private var _presetMoodMarker: MoodMarker
    private val _repository: IMoodMarkerRepository = MoodMarkerRepository(getApplication())
    private var _isEdit: Boolean = false

    init {
        viewModelScope.launch {
            _moodMarkerList.value = _repository.getMoodMarkers()
        }
        _entryToBeDeleted = null
        _presetMoodMarker = MoodMarker(0, EmotionType.Happy, "", false, Date().toString())
        _showDialog = mutableStateOf(false)
        showDialog = _showDialog
    }

    fun addMoodMarker(entry: MoodMarker){
        viewModelScope.launch {
            _repository.addMoodMarker(entry)
            _moodMarkerList.value = _repository.getMoodMarkers()
        }
    }

    fun deleteMoodMarker(){
        if (_entryToBeDeleted == null){
            return
        }
        viewModelScope.launch {
            _repository.deleteMoodMarker(_entryToBeDeleted!!)
            _moodMarkerList.value = _repository.getMoodMarkers()
            dismissDialog()
        }
    }

    fun dismissDialog() {
        _entryToBeDeleted = null
        _showDialog.value = false
    }

    fun prepareDelete(entry: MoodMarker) {
        _entryToBeDeleted = entry
        _showDialog.value = true
    }

    fun getFavorites(): List<MoodMarker> {
        return _moodMarkerList.value.filter { it.isFavorite }
    }

    fun updateMoodMarker(moodMarker: MoodMarker) {
        viewModelScope.launch {
            _repository.updateMoodMarker(moodMarker)
            _moodMarkerList.value = _repository.getMoodMarkers()
        }
    }

    fun toggleFavorite(moodMarker: MoodMarker) {
        val updateMood = MoodMarker(
            moodMarker.id,
            moodMarker.emotionType,
            moodMarker.dailyEntry,
            !moodMarker.isFavorite
        )
        updateMoodMarker(updateMood)
    }

    fun setPresetMoodMarker(moodMarker: MoodMarker) {
        _presetMoodMarker = moodMarker
    }

    fun getPresetMoodMarker(): MoodMarker {
        return _presetMoodMarker
    }

    fun setIsEdit() {
        _isEdit = !_isEdit
    }

    fun getIsEdit(): Boolean {
        return _isEdit
    }
}