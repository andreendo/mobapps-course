package com.example.boardofmessageapp_kt2.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private
    const val BASE_URL = "http://10.0.2.2:3000/"
    const val TO_TEST_URL = "http://localhost:3000/"

    val api: BoardInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BoardInterface::class.java)
    }

    val testApi: BoardInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TO_TEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BoardInterface::class.java)
    }
}