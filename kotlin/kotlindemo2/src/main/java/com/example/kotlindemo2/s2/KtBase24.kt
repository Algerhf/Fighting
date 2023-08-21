package com.example.kotlindemo2.s2

// TODO 24.kotlin语言的函数类型&隐式返回学习
fun main() {

    // 我们现在在写函数

    // 第一步：函数输入输出的声明
    val methodAction : () -> String

    // 第二步：对上面函数的实现
    methodAction = {
        val inputValue = 999999
        "$inputValue Derry"  // == 背后隐式 return "$inputValue Derry"
        // 匿名函数不要写return,最后一行就是返回值
    }

    // 第三步：调用此函数
    println(methodAction())
}

/*
// 相当于
fun methodAction() : String {
    return "Derry"
}
*/
