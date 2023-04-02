package com.example.jetpack.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveViewModel2:ViewModel() {
    val progress = MutableLiveData(0)
}