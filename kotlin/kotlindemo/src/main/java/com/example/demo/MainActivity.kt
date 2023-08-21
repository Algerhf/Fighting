package com.example.demo

import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import kotlin.properties.Delegates


class MainActivity : BaseActivity() {

    private lateinit var mTv: AppCompatTextView

    override fun getResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mTv = findViewById(R.id.tv)
    }

    override fun initData() {
        super.initData()
        mTv.text = "Hello"
    }

    override fun initListener() {
        super.initListener()
        mTv.setOnClickListener {
            Toast.makeText(mContext, "我被点击了", Toast.LENGTH_SHORT).show()
        }
    }
}