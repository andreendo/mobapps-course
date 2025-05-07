package com.example.login_kt2.repository

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun login(username : String, password : String) : String {
        delay(2000)

        return when {
            !listOf("user1", "user2").contains(username) -> "wrong_username"
            username == "user1" && password != "123" -> "wrong_password"
            username == "user1" && password == "123" -> "success"
            else -> "failed_connection"
        }
    }
}