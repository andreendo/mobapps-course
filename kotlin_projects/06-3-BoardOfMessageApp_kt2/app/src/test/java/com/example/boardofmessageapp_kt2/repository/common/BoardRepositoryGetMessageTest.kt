package com.example.boardofmessageapp_kt2.repository.common

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.HttpException

class BoardRepositoryGetMessageTest {

    val boardRepository = BoardRepository(true)

    @Before
    fun before() {
        runBlocking { boardRepository.reset() }
    }

    @Test
    fun `test GetMessages`() =  runBlocking {
        val msgs = boardRepository.getMessages(1)
        assertEquals(2, msgs.size)
    }

    @Test(expected = HttpException::class)
    fun `test GetMessages - no board`() =  runBlocking {
        val res = boardRepository.getMessages(1333)
    }
}