package com.example.lifecycles.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycles.databinding.ActivityLiveData1Binding

class LiveData1Activity : AppCompatActivity() {

    private val mModel: LiveViewModel1 by viewModels()
    private val mBinding by lazy {
        ActivityLiveData1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mModel.number.observe(this) { num ->
            mBinding.tvNum.text = num.toString()
        }

        lifecycle.addObserver(MyTimer {
            var num = mModel.number.value ?: 0
            mModel.number.postValue(++num)
        })
    }
}