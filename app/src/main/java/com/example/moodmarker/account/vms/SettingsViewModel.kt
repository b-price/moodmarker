package com.example.moodmarker.account.vms

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.db.IMoodMarkerRepository
import com.example.moodmarker.db.MoodMarkerRepository
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.moodEntries.EmotionType
import kotlinx.coroutines.launch
import java.util.Date

/** Viewmodel for settings screen - Not currently in use **/
class SettingsViewModel{


}