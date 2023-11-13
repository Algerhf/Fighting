package com.cuibaby.eat.db

import android.app.Application
import androidx.room.Room

class DatabaseManager {

    private lateinit var mApplication: Application

    val mDb: MyDataBase by lazy {
        Room.databaseBuilder(mApplication, MyDataBase::class.java, "eat.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun setApplication(application: Application) {
        this.mApplication = application
        mDb.createFoodDao()
    }

    companion object {
        @JvmStatic
        val instance: DatabaseManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DatabaseManager()
        }
    }
}