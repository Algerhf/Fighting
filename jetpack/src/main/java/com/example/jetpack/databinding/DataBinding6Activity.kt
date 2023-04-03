package com.example.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.R

class DataBinding6Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding6Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}