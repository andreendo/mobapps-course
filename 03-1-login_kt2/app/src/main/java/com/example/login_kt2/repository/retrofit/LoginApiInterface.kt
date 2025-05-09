package com.example.login_kt2.repository.retrofit

import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApiInterface {

    @POST("login")
    suspend fun login(@Body userData: UserData) : ResponseData

}