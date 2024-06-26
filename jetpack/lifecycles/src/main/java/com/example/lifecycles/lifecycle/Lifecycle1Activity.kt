package com.example.lifecycles.lifecycle

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycles.databinding.ActivityLifecycle1Binding

class Lifecycle1Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityLifecycle1Binding.inflate(layoutInflater)
    }

    private var mElapsedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onResume() {
        super.onResume()
        mBinding.chronometer.base = SystemClock.elapsedRealtime() - mElapsedTime
        mBinding.chronometer.start()
    }

    override fun onPause() {
        super.onPause()
        mElapsedTime = SystemClock.elapsedRealtime() - mBinding.chronometer.base
        mBinding.chronometer.stop()
    }
}