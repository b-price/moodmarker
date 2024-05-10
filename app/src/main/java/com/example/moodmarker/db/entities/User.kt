package com.example.moodmarker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//import java.sql.Time

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "firstName")
    val firstName : String,

    @ColumnInfo(name = "lastName")
    val lastName : String,

    @ColumnInfo(name = "userName")
    val userName : String,

    @ColumnInfo(name = "email")
    val email : String,

    @ColumnInfo(name = "password")
    val password : String,
) {

}