package com.example.lifecycles.lifecycle

import androidx.lifecycle.LifecycleService

class MyLocationService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        lifecycle.addObserver(MyLocationObserver(this))
    }
}