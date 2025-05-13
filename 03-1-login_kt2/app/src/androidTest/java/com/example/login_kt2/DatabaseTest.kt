package com.example.login_kt2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.login_kt2.repository.room.AppDatabase
import com.example.login_kt2.repository.room.LoginTryDao
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import com.example.login_kt2.repository.room.LoginTry

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var dao: LoginTryDao
    private lateinit var db: AppDatabase

    val LOGINTRY1 = LoginTry(username = "user1", passwordLength = 3, wasSuccessful = false, whenTried = "today")
    val LOGINTRY2 = LoginTry(username = "user1", passwordLength = 5, wasSuccessful = true, whenTried = "yesterday")


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        dao = db.loginTryDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testEmpty() = runTest {
        val list = dao.getAll()
        assertEquals(0, list.size)
    }

    @Test
    fun testInsertAndSelect() = runTest {
        dao.insert(LOGINTRY1)
        dao.insert(LOGINTRY2)
        val list = dao.getAll()
        assertEquals(2, list.size)
        assertTrue(list[0].id > list[1].id)
    }
}