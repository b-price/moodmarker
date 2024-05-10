package com.example.moodmarker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
//import java.sql.Time

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName : String,
    val lastName : String,
    val userName : String,
    val email : String,
    val password : String,
) {

}