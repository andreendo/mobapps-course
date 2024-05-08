package com.example.boardofmessageapp_kt2.repository.common

import com.example.boardofmessageapp_kt2.repository.retrofit.BoardInterface
import com.example.boardofmessageapp_kt2.repository.retrofit.RetrofitInstance
import retrofit2.http.Body
import retrofit2.http.Path

class BoardRepository(
    private val test: Boolean = false
) {
    private lateinit var client: BoardInterface

    init {
        if (test)
            client = RetrofitInstance.testApi
        else
            client = RetrofitInstance.api
    }

    suspend fun getBoards(): List<Board> = client.getBoards()

    suspend fun addBoard(board: Board) = client.addBoard(board)

    suspend fun deleteBoard(iid: Long) = client.deleteBoard(iid)

    suspend fun getMessages(iid: Long): List<Message> = client.getMessages(iid)

    suspend fun postMessage(iid: Long, message: Message): GeneralResponse = client.postMessage(iid, message)

    suspend fun reset(): GeneralResponse = client.reset()
}