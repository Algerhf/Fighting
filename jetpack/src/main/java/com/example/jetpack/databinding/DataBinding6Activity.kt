package com.example.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R

class DataBinding6Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityDataBinding6Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val list = getIdols()
        val adapter = MyAdapter(list)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this@DataBinding6Activity)
        mBinding.recyclerView.adapter = adapter
    }
}