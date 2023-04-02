package com.example.jetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityLiveData2Binding

class LiveData2Activity : AppCompatActivity() {

    private val mBinding by lazy{
        ActivityLiveData2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}