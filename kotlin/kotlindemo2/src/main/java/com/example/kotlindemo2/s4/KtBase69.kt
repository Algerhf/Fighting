package com.example.kotlindemo2.s4

// TODO 69.Kotlin语言的可变Map集合学习
// 1、可变集合的操作 += []  put
// 2、getOrPut 没有的情况
// 3、getOrPut 有的情况
fun main() {
    // 1、可变集合的操作 += []  put
    val map = mutableMapOf("Derry" to 123, "jack" to 456)

    map += "Michael" to 789
    map -= "Derry"
    map["CCC"] = 246
    map.put("DDD", 137)

    // 2、getOrPut 没有的情况
    // 如果整个map里面没有FFF元素，就新添加一个FFF元素到map集合中，然后获取该元素
    val r1 = map.getOrPut("FFF") { 666 }
    println(r1) // 666

    // 3、getOrPut 有的情况
    val r2 = map.getOrPut("jack") { 666 }
    println(r2) // 456

}