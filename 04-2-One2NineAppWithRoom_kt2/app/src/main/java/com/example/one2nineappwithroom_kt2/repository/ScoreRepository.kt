package com.example.one2nineappwithroom_kt2.repository

import kotlinx.coroutines.flow.Flow

class ScoreRepository(private val scoreDao: ScoreDao) {
    suspend fun insertScore(score: Score) = scoreDao.insert(score)

    suspend fun deleteAllScores() = scoreDao.deleteAllScores()

    fun getAllScores(): Flow<List<Score>> = scoreDao.getAllScores()

    suspend fun getBestScore(): Score = scoreDao.getBestScore()
}