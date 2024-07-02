package com.example.retrofit1app_kt2.service

class WeatherRepository {
    private val api = RetrofitInstance.api
    private val appid = "1b3d0e73c2837e05efe700a16d3756e2"

    suspend fun getWeatherByCityName(cityName: String, units: String = "metric") : WeatherResponse {
        return api.getWeatherByCityName(appid, cityName, units)
    }
}