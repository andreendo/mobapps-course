package com.example.camerademoexternal

import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    var photoUri by mutableStateOf<Uri?>(null)
        private set

    fun setPhoto(uri: Uri) {
        photoUri = uri
    }
}