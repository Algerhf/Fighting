package com.example.kotlindemo2.s6

// TODO 124.Kotlin语言的map变换函数
fun main() {
    val list = listOf("AAA", "BBB", "CCC")

    // 原理：就是把匿名函数的最后一行的返回值加入一个新的集合，并返回一个新的集合
    val list2 = list.map {
        "【$it】"
        // 888
    }

    println(list2)

    list.map {
        "姓名是：$it"
    }.map {
        "$it,文字的长度是：${it.length}"
    }.map {
        "【$it】"
    }.map {
        println("$it")
    }

}


