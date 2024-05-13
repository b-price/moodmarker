package com.example.moodmarker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

//import java.sql.Time

/** Database entity for user accounts and sets table name to be called users **/
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
//    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "firstName")
    var firstName : String,

    @ColumnInfo(name = "lastName")
    var lastName : String,

    @ColumnInfo(name = "userName")
    var userName : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "password")
    var password : String,
) {

}