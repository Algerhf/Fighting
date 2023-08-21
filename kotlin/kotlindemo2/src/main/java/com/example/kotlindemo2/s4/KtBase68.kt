package com.example.kotlindemo2.s4

// TODO 68.Kotlin语言的遍历 Map学习
fun main() {
    val map = mapOf("Derry" to 123, "jack" to 456)

    // 第一种方式
    map.forEach {
        println("key:${it.key}, value:${it.value}")
    }

    println()

    // 第二种方式
    map.forEach { key: String, value: Int ->
        println("key:$key, value:$value")
    }

    println()

    // 第三种方式
    map.forEach { (k, v) ->
        println("key: $k, value:$v")
    }

    println()

    // 第四种方式
    for (item in map) {
        println("key:${item.key},value:${item.value}")
    }

}