package com.example.retrofit1app_kt2.service

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("appid") appid: String,
        @Query("q") cityName: String,
        @Query("units") units: String
    ) : WeatherResponse

}