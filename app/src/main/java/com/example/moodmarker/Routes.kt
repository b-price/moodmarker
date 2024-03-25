package com.example.moodmarker

sealed class Routes(val route: String) {
    object MainPage : Routes("mainPage")
    object AddMoodMarker : Routes("addMoodMarker")
    object PastMoodMarkers : Routes("pastMoodMarkers")
    object FavMoodMarkers : Routes("favMoodMarkers")
    object Settings : Routes("settings")
    object ProfilePage : Routes("profilePage")
    object AppSettings : Routes("appSetings")
}