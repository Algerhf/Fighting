package com.example.kotlindemo2.s4

class KtBase80 {

    init {
        // init和成员属性是同时执行的，没初始化不能调用
        //number = number.times(9)
    }

    val number = 9

}

// TODO 80.Kotlin语言的初始化陷阱一学习
fun main() {

}