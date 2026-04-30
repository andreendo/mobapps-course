package com.example.maps

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    var latitude by mutableStateOf<Double?>(null)
        private set

    var longitude by mutableStateOf<Double?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun setLocation(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
        isLoading = false
        errorMessage = null
    }

    fun setLoadingState(value: Boolean) {
        isLoading = value
    }

    fun setError(message: String) {
        errorMessage = message
        isLoading = false
    }
}