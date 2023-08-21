package com.example.kotlindemo2.s4

// TODO 59.Kotlin语言的mutator函数学习
// 1、mutator += -=操作
// 2、removeIf
fun main() {
    // 1、mutator += -=操作
    val list = mutableListOf("Derry", "DerryAll", "DerryStr", "zhangsan")
    list += "李四" // mutator的特性+= -= 其实背后就是运算符重载而已
    list += "王五"
    list -= "Derry"
    println(list)

    // 2、removeIf
    // list.removeIf{ true}  // 删除所有元素
    list.removeIf { it.contains("Derr") } // 过滤所有元素，只要有"Derr"的元素就是true 删除
    println(list)
}