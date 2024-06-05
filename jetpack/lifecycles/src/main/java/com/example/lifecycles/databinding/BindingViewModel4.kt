package com.example.lifecycles.databinding

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 *
 * @author hufan
 * 创建日期：2023/4/3 17:11
 * 描述：
 *
 */
class BindingViewModel4(val user: User) : BaseObservable() {

    @Bindable
    fun getUserName(): String? {
        return user.userName
    }

    fun setUserName(newName:String?){
        if(user.userName != newName){
            user.userName = newName
            Log.d("hf","user.userName = ${user.userName}")
            //notifyPropertyChanged(BR.userName)
        }
    }
}