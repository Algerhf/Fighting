package com.example.kotlindemo2.s7

// 饿汉式
object SingletonDemo

// 懒汉式  KT实现
class SingletonDemo2 {
    companion object {
        private var instance: SingletonDemo2? = null
            get() {
                if (field == null) {
                    field = SingletonDemo2()
                }
                return field
            }

        @Synchronized  // 安全版本
        fun getInstanceAction() = instance!!
    }

    fun show() {
        println("show called.")
    }
}

// 懒汉式  KT实现  安全
class SingletonDemo3 private constructor() {
    companion object {
        val instance: SingletonDemo3 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo3()
        }
    }

    fun show() {
        println("show called.")
    }
}

// TODO 130.Kotlin语言的单例模式
fun main() {
    SingletonDemo3.instance.show()
}




