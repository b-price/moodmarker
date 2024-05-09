package com.example.moodmarker

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuoteViewModel(app: Application) : AndroidViewModel(app) {
    private val _quote: MutableState<String?> = mutableStateOf(null)
    val quote: State<String?> = _quote

    private val quotesFetcher = QuotesFetcher(getApplication())

    init {
        fetchQuote()
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            try {
                val fetchedQuotes = quotesFetcher.fetchQuotes()
                _quote.value = fetchedQuotes.firstOrNull()
            } catch (e: Exception) {
                Log.e("QuoteViewModel", "Error fetching quotes: ${e.message}", e)
                _quote.value = "Error fetching quotes"
            }
        }
    }
}
