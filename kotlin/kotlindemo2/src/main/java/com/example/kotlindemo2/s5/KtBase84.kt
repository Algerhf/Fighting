package com.example.kotlindemo2.s5

import java.io.File

// kt所有的类默认是final修饰的，不能被继承，和java相反
open class Person2(val name: String) {
    private fun showName() = "父类的名字是：name $name"

    // kt所有的函数默认是final修饰的，不能被继承，和java相反
    open fun myPrintln() = println(showName())
}

class Student2(val subName: String) : Person2(subName) {
    private fun showName() = "子类的名字是：name $name"

    override fun myPrintln() = println(showName())
}

// TODO 84.Kotlin语言的类型转换学习
// 2、is
// 3、is + as
fun main() {
    val p: Person2 = Student2("张三")
    p.myPrintln()

    println(p is Person2)
    println(p is Student2)
    println(p is File)

    if (p is Person2) {
        (p as Person2).myPrintln()
    }

    if (p is Student2) {
        (p as Student2).myPrintln()
    }
}