package com.example.login_kt2.repository

import com.example.login_kt2.repository.retrofit.LoginApiInterface
import com.example.login_kt2.repository.retrofit.RetrofitInstance
import com.example.login_kt2.repository.retrofit.UserData
import kotlinx.coroutines.delay

class UserRepository(val useTestUrl: Boolean = false) {

    private var client: LoginApiInterface

    init {
        client = if (useTestUrl) RetrofitInstance.testapi else RetrofitInstance.api
    }

    suspend fun login(username : String, password : String) : String {
        try {
            val res = client.login(UserData(username, password))
            return when(res.message) {
                "success" -> "success"
                "unexisting username" -> "wrong_username"
                "wrong password" -> "wrong_password"
                else -> res.message
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "failed_connection"
        }
    }

    suspend fun login_old(username : String, password : String) : String {
        delay(2000)

        return when {
            !listOf("user1", "user2").contains(username) -> "wrong_username"
            username == "user1" && password != "123" -> "wrong_password"
            username == "user1" && password == "123" -> "success"
            else -> "failed_connection"
        }
    }
}