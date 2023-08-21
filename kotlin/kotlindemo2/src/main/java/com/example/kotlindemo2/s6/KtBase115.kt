package com.example.kotlindemo2.s6

fun <T> T.showContentInfo() = println("${if (this is String) "你的字符串长度是：$length" else "你不是字符串"}")

// TODO 115.Kotlin语言的泛型扩展函数
fun main() {
    "Derry".showContentInfo()
    345.showContentInfo()
}

