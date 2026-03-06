package com.example.login_kt2.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginTryDao {

    @Insert
    suspend fun insert(loginTry : LoginTry)

    @Query("Select * from LoginTry Order by id DESC")
    suspend fun getAll() : List<LoginTry>

    @Query("Select * from LoginTry Order by id DESC")
    fun getAllInFlow() : Flow<List<LoginTry>>

}