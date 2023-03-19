package com.example.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.jetpack.databinding.ActivityStepOneBinding

class StepOneActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityStepOneBinding.inflate(layoutInflater)
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