package com.example.moodmarker

sealed class Routes(val route: String) {
    object MainPage : Routes("mainPage")
    object AddMoodMarker : Routes("addMoodMarker")
    object Entries : Routes("entries")
    object Favorites : Routes("favorites")
    object Settings : Routes("settings")
    object ProfilePage : Routes("profilePage")
    object AppSettings : Routes("appSettings")
    object Login : Routes("loginPage")
    object CreateAccount : Routes("createAccount")
}