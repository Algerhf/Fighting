package com.example.kotlindemo2.s6

// TODO 127.Kotlin语言的合并函数 - zip
fun main() {
    val names = listOf("张三", "李四", "王五")
    val ages = listOf(20, 21, 22)

    val zip: List<Pair<String, Int>> = names.zip(ages)
    println(zip)

    names.zip(ages) { n, a ->
        "$n=$a"
    }.run(::println)


}


