package com.example.kotlindemo2.s2

// TODO 28.kotlin语言的lambda学习
fun main() {
    val addResultMethod = { number1: Int, number2: Int ->
        "两数相加的结果是：${number1 + number2}"
    }// addResultMethod 函数 （Int,Int） -> String
    println(addResultMethod(1, 1))

    // 匿名函数的入参是Int         返回Any类型
    // lambda表达式的参数是Int    lambda的结果是Any类型
    val weekResultMethod = { number: Int ->
        when (number) {
            1 -> "星期1"
            2 -> "星期2"
            3 -> "星期3"
            4 -> "星期4"
            5 -> "星期5"
            else -> -1
        }
    }// weekResultMethod 函数： （Int） -> Any
    println(weekResultMethod(2))
}