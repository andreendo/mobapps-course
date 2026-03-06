package com.example.retrofit1app_kt2.service

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    val main: Main
)

data class Main(
    @SerializedName("temp")
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("sea_level")
    val seaLevel: Int
)
