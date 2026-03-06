package com.example.login_kt2.repository

import com.example.login_kt2.repository.room.LoginTry
import com.example.login_kt2.repository.room.LoginTryDao
import kotlinx.coroutines.flow.Flow

class LoginTryRepository(private val dao: LoginTryDao) {

    suspend fun insert(loginTry: LoginTry) = dao.insert(loginTry)

    fun getAll() : Flow<List<LoginTry>> = dao.getAllInFlow()

}