package com.example.boardofmessageapp_kt2.repository.common

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BoardRepositoryGetBoardsTest {

    val boardRepository = BoardRepository(true)

    @Before
    fun before() {
        runBlocking { boardRepository.reset() }
    }

    @Test
    fun `test GetBoards`() =  runBlocking {
        val boards = boardRepository.getBoards()
        assertEquals(2, boards.size)
        assertEquals("board_test1", boards[0].name)
        assertEquals(2, boards[0].messages.size)
        assertEquals("board_test2", boards[1].name)
        assertEquals(1, boards[1].messages.size)
    }
}