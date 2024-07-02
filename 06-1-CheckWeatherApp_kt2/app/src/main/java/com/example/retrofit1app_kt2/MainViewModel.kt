package com.example.retrofit1app_kt2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.retrofit1app_kt2.service.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = WeatherRepository()

    var wheatherLabel by mutableStateOf("")
    var loading by mutableStateOf(false)

    fun getWeather(city: String) {
        val cityName = if (city.trim().isEmpty()) "São Carlos" else city
        wheatherLabel = ""
        loading = true

        viewModelScope.launch {
            try {
                val response = repository.getWeatherByCityName(cityName)
                val label = "Temperature: %.2f C"
                wheatherLabel = String.format(label, response.main.temp)
            } catch (e: Exception) {
                wheatherLabel = "Error: " + e.message
            } finally {
                loading = false
            }
        }
    }
}