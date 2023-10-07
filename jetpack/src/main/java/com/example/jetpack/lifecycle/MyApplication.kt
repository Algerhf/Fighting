package com.example.jetpack.lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.jetpack.room.DatabaseManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
        DatabaseManager.instance.setApplication(this)
        DatabaseManager.instance.mDb.isOpen
    }
}