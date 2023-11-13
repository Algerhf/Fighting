package com.cuibaby.eat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuibaby.eat.db.Food
import com.cuibaby.eat.db.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val mFoodRepository by lazy {
        FoodRepository()
    }

    val mVegetableLiveData = MutableLiveData<MutableList<Food>>(mutableListOf())
    val mMeatLiveData = MutableLiveData<MutableList<Food>>(mutableListOf())

    fun insert(vararg foods: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            mFoodRepository.insert(*foods)
        }
    }

    fun queryAllVegetable() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mFoodRepository.queryAll(FoodType.VEGETABLE)
            if (list.isNotEmpty()) {
                mVegetableLiveData.postValue(list.toMutableList())
            } else {
                mVegetableLiveData.postValue(mutableListOf())
            }
        }
    }

    fun queryAllMeat() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mFoodRepository.queryAll(FoodType.MEAT)
            if (list.isNotEmpty()) {
                mMeatLiveData.postValue(list.toMutableList())
            } else {
                mMeatLiveData.postValue(mutableListOf())
            }
        }
    }
}