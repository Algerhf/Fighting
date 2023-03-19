package com.example.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.databinding.ActivityStepTwoBinding

class StepTwoActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityStepTwoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        lifecycle.addObserver(mBinding.chronometer)
    }
}