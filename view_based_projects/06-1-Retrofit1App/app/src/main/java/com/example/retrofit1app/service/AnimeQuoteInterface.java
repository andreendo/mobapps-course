package com.example.retrofit1app.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeQuoteInterface {

//    @GET("api/random")
    @GET("random")
    public Call<AnimeQuote> getRandomQuote();
}
