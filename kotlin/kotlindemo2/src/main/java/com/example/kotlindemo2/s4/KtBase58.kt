package com.example.kotlindemo2.s4

// TODO 58.Kotlin语言的可变List集合学习
// 可变集合
// 不可变集合  to  可变集合
// 可变集合    to  不可变集合
fun main() {
    // 可变集合
    val list = mutableListOf("Derry", "zs", "ww")
    list.add("ZL")
    list.remove("ww")
    println(list)

    // 不可变集合  to  可变集合
    val list2 = listOf(123, 456, 789)
    var list3 = list2.toMutableList()
    println(list3)

    // 可变集合    to  不可变集合
    val list4 = mutableListOf('A', 'B', 'C')
    var list5 = list4.toList()
    // list5.add('D')
}