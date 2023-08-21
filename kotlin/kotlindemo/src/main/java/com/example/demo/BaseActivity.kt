package com.example.demo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context
    private val a: String by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResId())
        ActivityManager.addActivity(this)
        mContext = this

        initView()
        initData()
        initListener()

    }

    abstract fun getResId(): Int

    abstract fun initView()

    open fun initData() {

    }

    open fun initListener() {

    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
    }
}