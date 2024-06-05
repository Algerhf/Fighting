package com.example.lifecycles.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycles.R

class DataBinding2Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.idol = Idol(R.mipmap.ic_wyb,"王一博",5)
    }
}