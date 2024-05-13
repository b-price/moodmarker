package com.example.moodmarker.quotes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import android.content.Context

//Interface for fetching quotes
interface IQuoteFetcher {
    suspend fun fetchQuotes(): List<String>
}
//Implements IQuoteFetcher interface
class QuotesFetcher (private val context: Context) : IQuoteFetcher {
    //OkHttpCLient instance for making HTTP requests
    private val client = OkHttpClient()

    //Fetch quotes from the ZenQuotes api
    override suspend fun fetchQuotes(): List<String> {
        return withContext(Dispatchers.IO) {
            //Build HTTP request ti fetch quotes
            val request = Request.Builder()
                .get()
                .url("https://zenquotes.io/api/quotes/")
                .build()
            //Execute the request synchronously
            val response = client.newCall(request).execute()
            val responseBody = response.body
            val quotes = mutableListOf<String>()
            //Parse response to check if it is successful and contains a body
            if (responseBody != null && response.isSuccessful) {
                //Parse JSON array that holds the quotes
                val jsonArray = JSONArray(responseBody.string())
                //Iterate through the JSON array to get the quotes
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val quoteText = jsonObject.getString("q")
                    quotes.add(quoteText)
                }
            }
            //Return the list of quotes
            quotes
        }
    }
}
