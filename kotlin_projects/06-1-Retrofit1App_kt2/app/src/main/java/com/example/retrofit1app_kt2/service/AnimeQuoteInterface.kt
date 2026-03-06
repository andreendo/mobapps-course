package com.example.retrofit1app_kt2.service

import retrofit2.http.GET

interface AnimeQuoteInterface {

    //    @GET("api/random")
    @GET("randomQuote")
    suspend fun getRandomQuote() : AnimeQuote

}