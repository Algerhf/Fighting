package com.example.kotlindemo2.s4

// TODO 67.Kotlin语言的读取map的值
// 方式一 [] 找不到会返回null
// 方式二 getOrDefault
// 方式三 getOrElse
// 方式四 与java一样会崩溃
fun main() {
    val map = mapOf("Derry" to 123, "jack" to 456)

    // 方式一 [] 找不到会返回null
    println(map["Derry"])  // 背后对[]运算符重载了
    println(map["jack"])
    println(map["xxx"])   // map通过key找  如果找不到返回null  不会崩溃

    // 方式二 getOrDefault
    println(map.getOrDefault("Derry", -1))
    println(map.getOrDefault("Derry2", -1))

    // 方式三 getOrElse
    println(map.getOrElse("Derry") { -1 })
    println(map.getOrElse("Derry2") { -1 })

    println()

    // 方式四 与java一样会崩溃
    println(map.getValue("Derry"))
    println(map.getValue("Derry2"))
}