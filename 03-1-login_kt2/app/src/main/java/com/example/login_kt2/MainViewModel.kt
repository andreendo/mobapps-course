package com.example.login_kt2

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_kt2.repository.LoginTryRepository
import com.example.login_kt2.repository.UserRepository
import com.example.login_kt2.repository.room.LoginTry
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel(val loginTryRepository: LoginTryRepository) : ViewModel() {

    var username by mutableStateOf("")
    var usernameError by mutableStateOf(false)
    var usernameErrorMessage by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordError by mutableStateOf(false)
    var passwordErrorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isLoginSuccessful by mutableStateOf(false)

    val userRepository = UserRepository()

    fun performLogin() {
        usernameError = false
        passwordError = false

        username = username.trim()
        if (username == "") {
            usernameError = true
            usernameErrorMessage = "empty"
            return
        }

        if (password == "") {
            passwordError = true
            passwordErrorMessage = "empty"
            return
        }

        viewModelScope.launch {
            isLoading = true
            var loginTry = LoginTry(
                username = username,
                passwordLength = password.length,
                whenTried = Date().toString(),
                wasSuccessful = false
            )
            val status = userRepository.login(username, password)
            isLoading = false
            Log.i("MainViewModel", "name: $username, password: $password, status: $status")
            when(status) {
                "success" -> {
                    loginTry = loginTry.copy(wasSuccessful = true)
                    Log.i("MainViewModel", "moving to main screen")
                    isLoginSuccessful = true
                }
                "wrong_username" -> {
                    usernameError = true
                    usernameErrorMessage = "wrong user name"
                }
                "wrong_password" -> {
                    passwordError = true
                    passwordErrorMessage = "wrong password"
                }
                else -> {
                    usernameError = true
                    usernameErrorMessage = "Error: $status"
                }
            }
            Log.i("MainViewModel", "insert login try: $loginTry")
            loginTryRepository.insert(loginTry)
        }
    }

    fun clearLogin() {
        username = ""
        password = ""
        usernameError = false
        passwordError = false
    }

    fun notifyTransition() {
        isLoginSuccessful = false
        clearLogin()
    }
}