package com.example.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    private val userInfo = MutableStateFlow(UserBean(0, "", 0))
    val _userInfo = userInfo.asStateFlow()

    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            val info = userRepository.getUserInfo(userId)
            userInfo.emit(info)
        }
    }
}