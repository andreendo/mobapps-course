package com.example.sharedpreferencesclicksapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

class AppViewModel(application: Application): AndroidViewModel(application) {
    var fMessage: String = ""
    var currentMessage by mutableStateOf("")
    private val repository = CounterRepository(application, viewModelScope)

    init {
        repository.observeCounter {
            currentMessage = String.format(fMessage, it)
        }
    }

    fun click() {
        repository.incrementCounter()
    }
}