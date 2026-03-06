package com.example.retrofit1app_kt2.service

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class WeatherRepositoryTest {

    private val repository = WeatherRepository()

    @Test
    fun `test GetWeatherByCityName successfully`(): Unit = runBlocking {
        val response = repository.getWeatherByCityName("São Carlos")
        assertTrue("positive temperature", response.main.temp > 0)
    }

    @Test
    fun `test GetWeatherByCityName with unexisting city`(): Unit = runBlocking {
        try {
            val response = repository.getWeatherByCityName("Dressrosa")
            fail()
        } catch (e: Exception) {
            assertEquals("HTTP 404 Not Found", e.message)
        }

    }
}