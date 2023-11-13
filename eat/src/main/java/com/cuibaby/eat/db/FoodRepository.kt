package com.cuibaby.eat.db

class FoodRepository {

    private val foodDao: FoodDao by lazy {
        DatabaseManager.instance.mDb.createFoodDao()
    }

    fun insert(vararg foods: Food) {
        foodDao.insert(*foods)
    }

    fun delete(vararg foods: Food) {
        val nameList = foods.map {
            it.name
        }
        foodDao.delete(nameList)
    }

    fun queryByName(foodType: String, foodName: String) = foodDao.queryByName(foodType, foodName)

    fun queryAll(foodType: String) = foodDao.queryAll(foodType)
}