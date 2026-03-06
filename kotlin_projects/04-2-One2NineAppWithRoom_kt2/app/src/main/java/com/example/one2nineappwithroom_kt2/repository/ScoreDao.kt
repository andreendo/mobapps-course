package com.example.one2nineappwithroom_kt2.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: Score)

    @Query("SELECT * FROM Score ORDER BY time ASC LIMIT 1")
    suspend fun getBestScore(): Score

    @Query("SELECT * FROM Score ORDER BY time ASC")
    fun getAllScores(): Flow<List<Score>>

    @Query("DELETE FROM Score")
    suspend fun deleteAllScores()
}