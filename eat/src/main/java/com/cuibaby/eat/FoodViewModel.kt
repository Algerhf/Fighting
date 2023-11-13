package com.cuibaby.eat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuibaby.eat.db.Food
import com.cuibaby.eat.db.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodViewModel : ViewModel() {

    private val mFoodRepository by lazy {
        FoodRepository()
    }

    val mFoodLiveData = MutableLiveData<MutableList<Food>>(mutableListOf())
    val mCurrentFoodType = MutableLiveData(FoodType.VEGETABLE)

    fun insert(vararg foods: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            mFoodRepository.insert(*foods)
        }
    }

    fun delete(vararg foods: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            mFoodRepository.delete(*foods)
        }
    }

    fun queryByName(foodName: String, callback: ((Boolean) -> Unit)? = null) {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                mFoodRepository.queryByName(mCurrentFoodType.value ?: FoodType.VEGETABLE, foodName)
            }
            callback?.invoke(list.isNotEmpty())
        }
    }

    fun queryAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mFoodRepository.queryAll(mCurrentFoodType.value ?: FoodType.VEGETABLE)
            if (list.isNotEmpty()) {
                mFoodLiveData.postValue(list.toMutableList())
            } else {
                mFoodLiveData.postValue(mutableListOf())
            }
        }
    }
}