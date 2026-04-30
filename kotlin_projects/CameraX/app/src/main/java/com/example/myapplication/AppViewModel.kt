package com.example.myapplication

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.camera.core.ImageCapture

class AppViewModel : ViewModel() {

    var photoUri by mutableStateOf<String?>(null)
        private set

    lateinit var imageCapture: ImageCapture

    fun setPhoto(uri: String) {
        photoUri = uri
    }
}

