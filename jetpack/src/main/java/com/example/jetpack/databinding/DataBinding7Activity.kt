package com.example.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class DataBinding7Activity : AppCompatActivity() {

    private val mModel:BindingViewModel7 by viewModels()

    private val mBinding by lazy {
        ActivityDataBinding7Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.model = mModel
        mBinding.lifecycleOwner = this
    }
}