package com.example.kotlincoroutine.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(entity = User::class)
    fun insert(vararg users: User)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

}