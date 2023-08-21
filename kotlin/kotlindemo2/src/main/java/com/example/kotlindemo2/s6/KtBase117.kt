package com.example.kotlindemo2.s6

val String.myInfo: String
    get() = "AAA"

// TODO 117.Kotlin语言的扩展属性
fun main() {
    val p = "aaa"
    println(p.myInfo)
}
