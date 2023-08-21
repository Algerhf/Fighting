 package com.example.kotlindemo2.s4

// TODO 61.Kotlin语言的解构语法过滤元素学习

fun main() {
    val list = listOf("李元霸", "李小龙", "李连杰")

    val (value1, value2, value3) = list
    println("value1:$value1, value2:$value2, value3:$value3")

    // 用_可以不接收赋值，节约一点性能
    val (_, n2, n3) = list
    // println(_)  _不是变量名，是用来过滤解构赋值的
    println("n2:$n2, n3:$n3")
}