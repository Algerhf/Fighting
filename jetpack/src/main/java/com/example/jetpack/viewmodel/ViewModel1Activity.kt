package com.example.jetpack.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.databinding.ActivityViewModel1Binding

class ViewModel1Activity : AppCompatActivity() {

    private val mModel: MyViewModel by viewModels()

    private val mBinding by lazy {
        ActivityViewModel1Binding.inflate(layoutInflater)
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
