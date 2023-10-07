package com.example.jetpack.databinding

import androidx.databinding.ObservableField

/**
 *
 * @author hufan
 * 创建日期：2023/4/3 17:11
 * 描述：
 *
 */
class BindingViewModel5(user: User){
    val userName = ObservableField<String>(user.userName)
}