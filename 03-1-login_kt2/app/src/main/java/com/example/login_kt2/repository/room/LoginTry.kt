package com.example.login_kt2.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginTry(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val username : String,
    val passwordLength : Int,
    val wasSuccessful : Boolean,
    val whenTried : String
)