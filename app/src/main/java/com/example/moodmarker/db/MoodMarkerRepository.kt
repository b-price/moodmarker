package com.example.moodmarker.db

import android.app.Application
import androidx.room.Room
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.db.entities.User

/** Repository for interacting with database **/
class MoodMarkerRepository(app: Application) : IMoodMarkerRepository {
    private val db: MoodMarkersDatabase
    init {
        db = Room.databaseBuilder(app, MoodMarkersDatabase::class.java, "moodmarkers.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /** MoodMarkers functions **/
    override suspend fun getMoodMarkers(): List<MoodMarker> {
        return db.moodMarkersDao().getMoodMarkers()
    }

    override suspend fun deleteMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().deleteMoodMarker(moodMarker)
    }

    override suspend fun addMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().addMoodMarker(moodMarker)
    }

    override suspend fun updateMoodMarker(moodMarker: MoodMarker){
        db.moodMarkersDao().updateMoodMarker(moodMarker)
    }


    /** Users functions **/
    override suspend fun getUsers(): List<User> {
        return db.usersDao().getUsers()
    }

    override suspend fun deleteUser(user: User){
        db.usersDao().deleteUser(user)
    }

    override suspend fun addUser(user: User){
        db.usersDao().addUser(user)
    }

    override suspend fun updateUser(user: User){
        db.usersDao().updateUser(user)
    }

    override suspend fun getUserInfo(id: Int): User {
        return db.usersDao().getUserInfo(id)
    }

    override suspend fun getLoginInfo(userName: String, password: String): User {
        return db.usersDao().getLoginInfo(userName = userName, password = password)
    }

    override suspend fun userExists(userName:String): Boolean{
        return db.usersDao().userExists(userName)
    }

}