package com.cuibaby.eat

import android.app.Application
import com.cuibaby.eat.db.DatabaseManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseManager.instance.setApplication(this)
        SpUtils.init(this)
    }
}