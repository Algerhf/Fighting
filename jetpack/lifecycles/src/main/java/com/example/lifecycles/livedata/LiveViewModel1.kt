package com.example.lifecycles.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveViewModel1:ViewModel() {
    var number = MutableLiveData(0)
}