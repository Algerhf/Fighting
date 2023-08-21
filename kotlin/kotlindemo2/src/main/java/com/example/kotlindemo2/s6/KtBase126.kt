package com.example.kotlindemo2.s6

// TODO 126.Kotlin语言的过滤函数 - filter
fun main() {
    val list = listOf("AAA", "BBBb", "CCC", "DDD")

    // 过滤掉不满足条件的元素
    list.filter {
        it.length == 3
    }.forEach {
        println(it)
    }
    // AAA
    // CCC
    // DDD

    val list2 = listOf(
        listOf(111, 222, 333),
        listOf(444, 555, 666),
        listOf(777, 888, 999)
    )

    list2.flatMap { it.filter { it > 400 } }.run(::println) // [444, 555, 666, 777, 888, 999]

}


