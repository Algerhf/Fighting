package com.example.kotlindemo2.s3

// TODO 46.Kotlin语言的==与===比较操作
fun main() {
    // == 值 内容的比较  相当于java中equals
    // === 引用的比较

    val name1 = "Derry"
    val name2 = "Derry"
    val name3 = "ww"

    println(name1.equals(name2)) // java的写法
    println(name1 == name2) // kt的写法
    println(name1 === name2)   // true
    println(name1 == name3)    // false

    val name4 = "derry".capitalize() // 修改成  "Derry"
    println(name4 === name1)  // false
}
