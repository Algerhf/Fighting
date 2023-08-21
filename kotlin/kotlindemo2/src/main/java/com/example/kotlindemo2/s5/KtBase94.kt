package com.example.kotlindemo2.s5

data class AddClass(var number1: Int, var number2: Int) {
    operator fun plus(p1: AddClass): Int {
        return number1 + p1.number1 + number2 + p1.number2;
    }
}

// TODO 94.Kotlin语言的运算符重载学习
fun main() {
    println(AddClass(1, 1) + AddClass(1, 1))
}

