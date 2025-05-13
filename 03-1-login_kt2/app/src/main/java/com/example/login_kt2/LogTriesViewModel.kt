package com.example.login_kt2

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_kt2.repository.LoginTryRepository
import com.example.login_kt2.repository.room.LoginTry
import kotlinx.coroutines.launch

class LogTriesViewModel(val loginTryRepository: LoginTryRepository) : ViewModel() {

    var loginTries = mutableStateListOf<LoginTry>()

    init {
        viewModelScope.launch {
            loginTryRepository.getAll().collect {
                loginTries.clear()
                it.forEach { v -> loginTries.add(v) }
            }
        }
    }

}