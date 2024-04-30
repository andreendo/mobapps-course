package com.example.one2nineappwithroom_kt2.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerName: String,
    val time: Float,
    val whenPlayed: String
)