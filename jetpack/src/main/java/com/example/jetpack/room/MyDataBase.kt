package com.example.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *
 * @author hufan
 * 创建日期：2023/4/14 18:04
 * 描述：
 *
 */

@Database(entities = [Student::class], version = 1, exportSchema = true)
abstract class MyDataBase : RoomDatabase() {
    val mStudentDao by lazy {
        createStudentDao()
    }
    abstract fun createStudentDao(): StudentDao
}