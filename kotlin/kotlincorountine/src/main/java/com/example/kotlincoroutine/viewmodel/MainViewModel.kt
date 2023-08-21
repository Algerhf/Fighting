package com.example.kotlincoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincoroutine.bean.User
import com.example.kotlincoroutine.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    val userLiveData = MutableLiveData<User>()
    val userRepository: UserRepository by lazy { UserRepository() }

    fun getUser(name: String) {
        viewModelScope.launch {
            userLiveData.value = userRepository.getUser(name)
        }
    }
}