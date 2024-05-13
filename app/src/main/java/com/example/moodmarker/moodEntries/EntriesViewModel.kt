package com.example.moodmarker.moodEntries

import android.Manifest
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.R
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
    val CHANNEL_ID = "submission_channel"

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

    fun displayNotification() {
        // Create a notification channel
        createNotificationChannel()

        // Build the notification
        val builder = NotificationCompat.Builder(getApplication(), CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Submission Confirmation")
            .setContentText("You submitted your daily MoodMarker. See you tomorrow!")
            .setStyle(NotificationCompat.BigTextStyle().bigText("You submitted your daily MoodMarker. See you tomorrow!"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // Display the notification
        with(NotificationManagerCompat.from(getApplication())) {
            val context = getApplication<Application>().applicationContext
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider handling the case where the user hasn't granted the permission.
                return
            }
            notify(0, builder.build())
        }
    }


    // Add a method to create the notification channel
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Submission"
            val descriptionText = "Channel for confirming submission of a daily mood marker"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getApplication<Application>().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}