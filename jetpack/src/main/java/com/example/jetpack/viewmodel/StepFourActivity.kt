package com.example.jetpack.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.databinding.ActivityStepFourBinding

class StepFourActivity : AppCompatActivity() {

    private val mModel: MyViewModel by viewModels()

    private val mBinding by lazy {
        ActivityStepFourBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.tvNum.text = mModel.number.toString()

        mBinding.btnAdd.setOnClickListener {
            mBinding.tvNum.text = (++mModel.number).toString()
        }
    }
}
