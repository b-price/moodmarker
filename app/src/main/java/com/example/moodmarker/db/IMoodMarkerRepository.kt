package com.example.moodmarker.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.db.entities.User

interface IMoodMarkerRepository {

    /** MoodMarkers **/
    suspend fun getMoodMarkers() : List<MoodMarker>

    suspend fun deleteMoodMarker(moodMarker: MoodMarker)

    suspend fun addMoodMarker(moodMarker: MoodMarker)

    suspend fun updateMoodMarker(moodMarker: MoodMarker)


    /** Users **/
    suspend fun getUsers() : List<User>

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun getUserInfo(id:Int): User

    suspend fun getLoginInfo(userName: String, password: String): User

    suspend fun userExists(userName:String): Boolean
}