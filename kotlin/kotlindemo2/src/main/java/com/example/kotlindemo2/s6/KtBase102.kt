package com.example.kotlindemo2.s6

abstract class BaseActivity {
    fun onCreate() {
        setContentView(getLayoutId())
        initView()
        initData()
        initXXX()
    }

    abstract fun getLayoutId(): Int

    abstract fun initXXX()

    abstract fun initData()

    abstract fun initView()

    private fun setContentView(layoutId: Int) = ""
}

class MainActivity : BaseActivity() {
    override fun getLayoutId() = 456

    override fun initXXX() = println("初始化")

    override fun initData() = println("初始化Data")

    override fun initView() = println("初始化VIEW")
}

// TODO 102.Kotlin语言的抽象类
fun main() {
    MainActivity().onCreate()
}

