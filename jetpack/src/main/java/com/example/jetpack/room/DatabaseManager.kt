package com.example.jetpack.room

import android.app.Application
import androidx.room.Room

/**
 *
 * @author hufan
 * 创建日期：2023/4/14 18:31
 * 描述：
 *
 */
class DatabaseManager private constructor() {

    private lateinit var mApplication: Application

    val mDb: MyDataBase by lazy {
        Room.databaseBuilder(mApplication, MyDataBase::class.java, "my_db.db")
            .allowMainThreadQueries()
            .addMigrations()
            .build()
    }

    companion object {
        val instance: DatabaseManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DatabaseManager()
        }
    }

    fun setApplication(application: Application) {
        this.mApplication = application
    }
}