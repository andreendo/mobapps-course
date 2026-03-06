package com.example.login_kt2.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// objeto singleton
object RetrofitInstance {
    private
    const val BASE_URL = "http://10.0.2.2:3001/"
    const val TO_TEST_BASE_URL = "http://localhost:3001/"

    val api : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }

    val testapi : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TO_TEST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }
}