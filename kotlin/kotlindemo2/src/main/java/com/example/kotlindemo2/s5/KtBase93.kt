package com.example.kotlindemo2.s5

data class KtBase93(var name: String, var age: Int) {

    init {
        println("主构造函数执行了")
    }

    constructor(name: String) : this(name, 18) {
        println("次构造函数执行了")
    }

}

// TODO 93.Kotlin语言的解构声明
fun main() {

    val (name, age) = KtBase93("张安", 25)
    println(name)
    println(age)

}

