package com.example.moodmarker.account

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.db.IMoodMarkerRepository
import com.example.moodmarker.db.MoodMarkerRepository
import com.example.moodmarker.db.entities.User
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application): AndroidViewModel(app) {
    private val _userList: MutableState<List<User>> = mutableStateOf(listOf())
    val userList: State<List<User>> = _userList
    private val _repository: IMoodMarkerRepository = MoodMarkerRepository(getApplication())

    init {
        viewModelScope.launch {
            _userList.value = _repository.getUsers()
        }
//        _entryToBeDeleted = null
//        _presetMoodMarker = MoodMarker(0, EmotionType.Happy, "", false, Date().toString())
//        _showDialog = mutableStateOf(false)
//        showDialog = _showDialog
    }
}