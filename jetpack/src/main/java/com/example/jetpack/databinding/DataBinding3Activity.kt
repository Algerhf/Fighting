package com.example.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.R

class DataBinding3Activity : AppCompatActivity() {

    private val mBinding by lazy{
        ActivityDataBinding3Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val url = "https://img2.baidu.com/it/u=955668699,2826406588&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
        mBinding.networkImage = url
        mBinding.localImage = R.mipmap.ic_launcher
    }
}