package com.example.moodmarker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EntriesViewModel: ViewModel() {
    private val _moodMarkerList: MutableState<List<MoodMarker>> = mutableStateOf(listOf())
    val moodMarkerList: State<List<MoodMarker>> = _moodMarkerList
    private var _entryToBeDeleted: MoodMarker?
    private val _showDialog: MutableState<Boolean>
    val showDialog: State<Boolean>
    private val _repository: MoodMarkerRepository = MoodMarkerRepository()

    init {
        _moodMarkerList.value = _repository.getMoodMarkers()
        _entryToBeDeleted = null
        _showDialog = mutableStateOf(false)
        showDialog = _showDialog
    }

    fun addMoodMarker(entry: MoodMarker){
        _repository.addMoodMarker(entry)
        _moodMarkerList.value = _repository.getMoodMarkers()
    }

    fun deleteMoodMarker(){
        if (_entryToBeDeleted == null){
            return
        }
        _repository.deleteMoodMarker(_entryToBeDeleted!!)
        _moodMarkerList.value = _repository.getMoodMarkers()
        dismissDialog()
    }

    /**
     * Clears the cached song and dismisses the dialog.
     */
    fun dismissDialog() {
        _entryToBeDeleted = null
        _showDialog.value = false
    }

    /**
     * Caches the song to be deleted and shows the dialog
     */
    fun prepareDelete(entry: MoodMarker) {
        _entryToBeDeleted = entry
        _showDialog.value = true
    }
}