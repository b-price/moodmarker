package com.example.moodmarker.quotes

import android.Manifest
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.R
import kotlinx.coroutines.launch

class QuoteViewModel(app: Application) : AndroidViewModel(app) {
    private val _quote: MutableState<String?> = mutableStateOf(null)
    val quote: State<String?> = _quote

    private val quotesFetcher = QuotesFetcher(getApplication())

    init {
        fetchQuote()
    }

    fun fetchQuote() {
        viewModelScope.launch {
            try {
                val fetchedQuotes = quotesFetcher.fetchQuotes()
                _quote.value = fetchedQuotes.random()
            } catch (e: Exception) {
                Log.e("QuoteViewModel", "Error fetching quotes: ${e.message}", e)
                _quote.value = "Error fetching quotes"
            }
        }
    }

}

