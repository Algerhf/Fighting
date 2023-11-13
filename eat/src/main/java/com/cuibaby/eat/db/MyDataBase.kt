package com.cuibaby.eat.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = true)
abstract class MyDataBase : RoomDatabase() {
    abstract fun createFoodDao(): FoodDao
}