package com.example.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.R

class DataBinding2Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}