package com.cuibaby.eat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(entity = Food::class)
    fun insert(vararg foods: Food)

    @Query("DELETE FROM food WHERE name in (:foodNames)")
    fun delete(foodNames: List<String>)

    @Query("SELECT * FROM food WHERE name=:foodName AND foodType=:foodType")
    fun queryByName(foodType: String, foodName: String): List<Food>

    @Query("SELECT * FROM food WHERE foodType=:foodType")
    fun queryAll(foodType: String): List<Food>
}