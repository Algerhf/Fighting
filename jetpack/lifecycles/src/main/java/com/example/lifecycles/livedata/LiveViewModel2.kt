package com.example.lifecycles.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveViewModel2:ViewModel() {
    val progress = MutableLiveData(0)
}