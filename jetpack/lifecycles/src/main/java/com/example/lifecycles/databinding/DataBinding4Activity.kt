package com.example.lifecycles.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DataBinding4Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding4Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.model = BindingViewModel4(User("zhangsan"))
    }
}