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
    //MutableState Holds the fetched quote
    private val _quote: MutableState<String?> = mutableStateOf(null)
    //Immutable state represents the fetched quote
    val quote: State<String?> = _quote
    //QuotesFetcher instance
    private val quotesFetcher = QuotesFetcher(getApplication())

    init {
        fetchQuote() //Fetch quote when ViewModel is initialized
    }

    //Function to fetch a random quote asynchronously
    fun fetchQuote() {
        viewModelScope.launch {
            try {
                //Fetch quote from the QuotesFetcher
                val fetchedQuotes = quotesFetcher.fetchQuotes()
                //set random quote from fetched quotes
                _quote.value = fetchedQuotes.random()
            } catch (e: Exception) {
                //Log any errors that occur during the fetching
                Log.e("QuoteViewModel", "Error fetching quotes: ${e.message}", e)
                //set an error message if fetching fails
                _quote.value = "Error fetching quotes"
            }
        }
    }

}

