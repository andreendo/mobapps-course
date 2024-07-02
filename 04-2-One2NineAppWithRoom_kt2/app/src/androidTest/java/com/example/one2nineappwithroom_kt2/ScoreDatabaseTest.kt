package com.example.one2nineappwithroom_kt2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.one2nineappwithroom_kt2.repository.GameDatabase
import com.example.one2nineappwithroom_kt2.repository.Score
import com.example.one2nineappwithroom_kt2.repository.ScoreDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ScoreDatabaseTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var scoreDao: ScoreDao
    private lateinit var db: GameDatabase

    val SCORE1 = Score(playerName = "player 1", time = 45.77f, whenPlayed = "some day")
    val SCORE2 = Score(playerName = "player 2", time = 15.77f, whenPlayed = "some other day")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, GameDatabase::class.java)
            .build()
        scoreDao = db.scoreDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testEmpty() = runTest {
        scoreDao.getAllScores().test {
            val list = awaitItem()
            Assert.assertEquals(0, list.size)
            cancel()
        }
    }

    @Test
    fun testInsertAndSelectInOrder() = runTest {
        scoreDao.insert(SCORE1)
        scoreDao.insert(SCORE2)
        scoreDao.getAllScores().test {
            val list = awaitItem()
            Assert.assertEquals(2, list.size)
            Assert.assertEquals("player 2", list.get(0).playerName)
            cancel()
        }
    }

    @Test
    fun testDelete() = runTest {
        scoreDao.insert(SCORE1)
        scoreDao.insert(SCORE2)
        scoreDao.deleteAllScores()
        scoreDao.getAllScores().test {
            val list = awaitItem()
            Assert.assertEquals(0, list.size)
            cancel()
        }
    }

    @Test
    fun testBestScore() = runTest {
        scoreDao.insert(SCORE1)
        scoreDao.insert(SCORE2)
        val bestScore = scoreDao.getBestScore()
        Assert.assertEquals("player 2", bestScore.playerName)
    }

    @Test
    fun testBestScoreEmpty() = runTest {
        val bestScore = scoreDao.getBestScore()
        Assert.assertNull(bestScore)
    }

}