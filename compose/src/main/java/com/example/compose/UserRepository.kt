package com.example.compose

import javax.inject.Inject

class UserRepository @Inject constructor() {
    suspend fun getUserInfo(userId: String) = UserBean(54321, "张三", 18)
}

data class UserBean(val id: Int, val name: String, val age: Int)