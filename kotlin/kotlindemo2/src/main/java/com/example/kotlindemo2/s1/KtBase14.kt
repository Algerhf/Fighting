package com.example.kotlindemo2.s1

// TODO 14.kotlin语言的 when表达式
fun main() {
    val week = 8

    // java的if是语句
    // KT的if是表达式，有返回值

    // Any 相当于java的Object
    val info = when(week){
        1 -> "今天是星期一"
        2 -> "今天是星期二"
        3 -> "今天是星期三"
        4 -> "今天是星期四"
        5 -> "今天是星期五"
        6 -> "今天是星期六"
        7 -> "今天是星期日"   // 返回String
        else ->{
            println("忽略星期几")  // 返回Unit
        }
    }

    println(info) // void 关键字  无返回
    // Unit是一个类，用来代替java中的void关键字
}