package com.example.retrofit1app.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimeQuoteInterface {

    @GET("api/random")
    public Call<AnimeQuote> getRandomQuote();

    @GET("api/quotes/character")
    public Call<List<AnimeQuote>> getQuotesFor(@Query("name") String name);
}
