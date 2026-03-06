package com.example.checkweatherapp.weatherService;

import com.example.checkweatherapp.weatherService.response.WeatherResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("weather")
    public Call<JsonElement> getWeatherByCityName(
            @Query("appid") String appid,
            @Query("q") String cityName,
            @Query("units") String units
            );

    @GET("weather")
    public Call<WeatherResponse> getWeatherByCityNameV2(
            @Query("appid") String appid,
            @Query("q") String cityName,
            @Query("units") String units
    );
}
