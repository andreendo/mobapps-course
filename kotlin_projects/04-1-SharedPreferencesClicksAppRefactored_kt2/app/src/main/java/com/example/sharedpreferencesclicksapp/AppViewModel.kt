package com.example.sharedpreferencesclicksapp

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel(application: Application): AndroidViewModel(application) {
    var counter by mutableStateOf(0)

    private val repository = CounterRepository(application)

    init {
        viewModelScope.launch {
            repository.observeCounter {
                Log.i("AppViewModel", "Counter changed to $it")
                counter = it
            }
        }
    }

    fun click() {
        Log.i("AppViewModel", "Current counter: $counter")
        viewModelScope.launch {
            repository.incrementCounter()
        }
    }
}