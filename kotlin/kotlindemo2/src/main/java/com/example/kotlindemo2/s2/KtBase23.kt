package com.example.kotlindemo2.s2

// TODO 23.kotlin语言的匿名函数学习
fun main() {
    val count = "Derry".count()
    println(count) // 5

    val len2 = "Derry".count{
        // it 等价于 D  e  r  r  y 的字符Char
        it == 'r'
    }
    println(len2) // 2
}