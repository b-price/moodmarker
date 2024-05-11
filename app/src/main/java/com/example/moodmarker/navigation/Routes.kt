package com.example.moodmarker.navigation

sealed class Routes(val route: String) {
    object MainPage : Routes("mainPage")
    object AddMoodMarker : Routes("addMoodMarker")
    object Entries : Routes("entries")
    object Favorites : Routes("favorites")
    object Settings : Routes("settings")
    object ProfilePage : Routes("profilePage")
    object AppSettings : Routes("appSettings")
    object LoginPage : Routes("loginPage")
    object CreateAccount : Routes("createAccount")
    object ChangePassword : Routes("changePassword")
    object EditProfile : Routes("editProfile")

}