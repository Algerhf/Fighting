package com.example.lifecycles.livedata

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*

class MyTimer(val method: (() -> Unit)?) : Timer(), DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        schedule(object : TimerTask() {
            override fun run() {
                method?.invoke()
            }
        }, 1000, 1000)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        cancel()
    }

}