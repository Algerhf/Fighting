package com.example.lifecycles.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycles.R
import com.example.lifecycles.databinding.ActivityLiveData2Binding

class LiveData2Activity : AppCompatActivity() {

    private val mBinding by lazy{
        ActivityLiveData2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}