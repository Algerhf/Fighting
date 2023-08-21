package com.example.kotlindemo2.s4

// TODO 63.Kotlin语言的可变Set集合
fun main() {
    val set = mutableSetOf("lisi", "wangwu")
    set += "zhaoliu"
    set += "laoqi"
    println(set)
}