package com.example.boardofmessageapp_kt2.repository.retrofit

import com.example.boardofmessageapp_kt2.repository.common.Board
import com.example.boardofmessageapp_kt2.repository.common.GeneralResponse
import com.example.boardofmessageapp_kt2.repository.common.Message
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface BoardInterface {

    @GET("board")
    suspend fun getBoards() : List<Board>

    @POST
    suspend fun addBoard(@Body board: Board): Board

    @GET("board/{iid}")
    suspend fun getBoard(@Path("iid") iid: Long): Board

    @DELETE("board/{iid}")
    suspend fun deleteBoard(@Path("iid") iid: Long): GeneralResponse

    @POST("board/{iid}/messages")
    suspend fun postMessage(@Path("iid") iid: Long, @Body message: Message): GeneralResponse

    // only for testing
    @GET("reset")
    suspend fun reset(): GeneralResponse

    @GET("board/{iid}/messages")
    suspend fun getMessages(@Path("iid") iid: Long): List<Message>

}