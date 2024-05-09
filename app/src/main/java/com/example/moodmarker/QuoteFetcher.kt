package com.example.moodmarker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import android.content.Context


interface IQuoteFetcher {
    suspend fun fetchQuotes(): List<String>
}

class QuotesFetcher (private val context: Context) : IQuoteFetcher {
    private val client = OkHttpClient()

    override suspend fun fetchQuotes(): List<String> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url("https://type.fit/api/quotes")
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body
            val quotes = mutableListOf<String>()

            if (responseBody != null && response.isSuccessful) {
                val jsonArray = JSONArray(responseBody.string())

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val quoteText = jsonObject.getString("text")
                    quotes.add(quoteText)
                }
            }

            quotes
        }
    }
}
