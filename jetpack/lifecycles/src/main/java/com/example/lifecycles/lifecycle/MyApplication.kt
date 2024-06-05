package com.example.lifecycles.lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.lifecycles.room.DatabaseManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
        DatabaseManager.instance.setApplication(this)
        DatabaseManager.instance.mDb.isOpen
    }
}