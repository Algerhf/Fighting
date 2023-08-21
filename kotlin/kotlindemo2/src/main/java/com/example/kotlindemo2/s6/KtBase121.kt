package com.example.kotlindemo2.s6

// as g 重命名扩展操作
import com.example.kotlindemo2.s6.com.randomItemValue as g

// TODO 121.Kotlin语言的重命名扩展
fun main() {
    val list = listOf(123, 456, 789)
    println(list.g())

    val set = setOf(123, 456, 789)
    println(set.g())
}
