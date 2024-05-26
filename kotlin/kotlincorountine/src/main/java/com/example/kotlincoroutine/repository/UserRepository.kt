package com.example.kotlincoroutine.repository

import com.example.kotlincoroutine.bean.Users

class UserRepository {
    suspend fun getUser(name: String) = Users(name, "")//userServiceApi.getUser(name)
}