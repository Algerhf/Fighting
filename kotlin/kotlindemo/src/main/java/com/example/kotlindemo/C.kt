package com.example.kotlindemo

open class C {
    fun foo() {
        println("成员函数")
    }
}

fun Any?.toString(): String {
    if (this == null) return "null"

    return toString()
}

val C.name: String
    get() = "张三"


fun main(args: Array<String>) {
    val c = C()
    println("name=${c.name}")

}