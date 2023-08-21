package com.example.kotlindemo2.s6

// TODO 125.Kotlin语言的flatMap变换函数
// map {返回类型：it == 每一个元素 T String Int Boolean Char}
// flatMap { 返回类型： it == 每一个元素T 集合1 集合2 集合3 ...是把每一个元素（集合）加入到新集合，最后返回新集合}
fun main() {
    val list = listOf("AAA", "BBB", "CCC", "DDD")

    // 1、相当于把集合中的每一个元素单独拿出来，把这个元素转换成一个集合
    // 2、再把所有元素生成的集合  添加到一个新集合中并返回
    list.flatMap {
        listOf("111$it", "222$it", "333$it")
    }.forEach {
        println(it)
    }

}


