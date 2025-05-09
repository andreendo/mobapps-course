package com.example.login_kt2.repository

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class UserRepositoryTest {

    val userRepository = UserRepository(true)

    @Test
    fun loginSuccessful() = runBlocking {
        val res = userRepository.login("user1", "123")
        assertEquals("success", res)
    }

    @Test
    fun loginWrongUsername() = runBlocking {
        val res = userRepository.login("andre", "123")
        assertEquals("wrong_username", res)
    }

    @Test
    fun loginWrongPassword() = runBlocking {
        val res = userRepository.login("user1", "123wrong")
        assertEquals("wrong_password", res)
    }
}