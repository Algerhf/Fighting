package com.example.kotlincoroutine.repository

import com.example.kotlincoroutine.bean.User

class UserRepository {
    suspend fun getUser(name: String) = User(name,"")//userServiceApi.getUser(name)
}