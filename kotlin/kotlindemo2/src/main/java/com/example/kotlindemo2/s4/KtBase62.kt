package com.example.kotlindemo2.s4

// TODO 62.Kotlin语言的Set创建与元素的获取
// 1、Set定义  不允许重复
// 2、普通方式 elementAt 会越界崩溃
// 3、elementAtOrElse elementAtOrNull
fun main() {
    val set = setOf("lisi", "wangwu", "zhaoliu", "zhaoliu") // Set集合不会有重复元素
    println(set)

    // set[0] 没有这样 []的功能，去获取Set集合元素
    println(set.elementAt(0)) // [0]
    println(set.elementAt(1))
    println(set.elementAt(2))
//    println(set.elementAt(3)) // [3] 崩溃 会越界
//    println(set.elementAt(4)) // [4] 崩溃 会越界

    println()

    // 使用Set集合，尽量使用此方法，防止越界崩溃
    println(set.elementAtOrElse(0) { "越界了" })
    println(set.elementAtOrElse(100) { "越界了" })

    println(set.elementAtOrNull(0))
    println(set.elementAtOrNull(111))
    // OrNull + 空合并操作符  一起使用
    println(set.elementAtOrNull(88) ?: "你越界啦啊")
}