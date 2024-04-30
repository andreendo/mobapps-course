package com.example.retrofit1app_kt2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.retrofit1app_kt2.service.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.api

    var quote by mutableStateOf("")
    var loading by mutableStateOf(false)

    fun newQuote() {
        quote = ""
        loading = true

        viewModelScope.launch {
            try {
                val randomQuote = api.getRandomQuote()
                quote = randomQuote.quote
            } catch (e: Exception) {
                quote = "Error"
            } finally {
                loading = false
            }
        }
    }
}