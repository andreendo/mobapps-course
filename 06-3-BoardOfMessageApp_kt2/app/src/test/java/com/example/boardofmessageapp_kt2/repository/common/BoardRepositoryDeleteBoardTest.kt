package com.example.boardofmessageapp_kt2.repository.common

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.HttpException

class BoardRepositoryDeleteBoardTest {

    val boardRepository = BoardRepository(true)

    @Before
    fun before() {
        runBlocking { boardRepository.reset() }
    }

    @Test
    fun `test Delete Existing Board`() =  runBlocking {
        val response = boardRepository.deleteBoard(1)
        assertEquals("Board removed", response.message)
    }

    @Test(expected = HttpException::class)
    fun `test Delete Inexisting Board`() =  runBlocking {
        val response = boardRepository.deleteBoard(1333)
    }

}