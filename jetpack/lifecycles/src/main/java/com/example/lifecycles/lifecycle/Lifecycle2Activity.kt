package com.example.lifecycles.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycles.databinding.ActivityLifecycle2Binding

class Lifecycle2Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityLifecycle2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        lifecycle.addObserver(mBinding.chronometer)
    }
}