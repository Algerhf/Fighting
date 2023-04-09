package com.example.jetpack.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityRoom1Binding

class Room1Activity : AppCompatActivity() {

    private val mViewModel:RoomViewModel1 by viewModels()

    private val mBinding by lazy {
        ActivityRoom1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.model = mViewModel
        mBinding.lifecycleOwner = this

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = StudentAdapter(mutableListOf())
    }
}