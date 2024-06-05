package com.example.lifecycles.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycles.R

class DataBinding1Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.idol = Idol(R.mipmap.ic_wyb,"王一博",5)
        mBinding.eventHandle = EventHandle()
    }
}