package com.example.moodmarker.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.db.entities.User

/** MoodMarkers Dao **/
@Dao
interface MoodMarkersDao {
    @Query("select * from moodmarkers")
    suspend fun getMoodMarkers(): List<MoodMarker>
    @Insert
    suspend fun addMoodMarker(moodMarker: MoodMarker)
    @Delete
    suspend fun deleteMoodMarker(moodMarker: MoodMarker)
    @Update
    suspend fun updateMoodMarker(moodMarker: MoodMarker)

}

/** Users Dao **/
@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>
    @Insert
    suspend fun addUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)
    @Update
    suspend fun updateUser(user: User)

    /** Get User Info **/
    @Query("SELECT * FROM users WHERE id LIKE :id")
    suspend fun getUserInfo(id:Int): User

    /** Get Login Info **/
    @Query("SELECT * FROM users WHERE userName LIKE :userName AND password LIKE :password")
    suspend fun getLoginInfo(userName: String, password: String): User

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE userName LIKE :userName)")
    suspend fun userExists(userName:String): Boolean
}


/** Database Implementation **/
@Database(entities = [MoodMarker::class, User::class], version = 7, exportSchema = true)
abstract class MoodMarkersDatabase : RoomDatabase() {
    abstract fun moodMarkersDao(): MoodMarkersDao
    abstract fun usersDao(): UsersDao
}