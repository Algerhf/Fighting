package com.example.kotlindemo2.s5

// kt所有的类默认是final修饰的，不能被继承，和java相反
open class Person(val name: String) {
    private fun showName() {
        println("人的名字是：name $name")
    }

    // kt所有的函数默认是final修饰的，不能被继承，和java相反
    open fun myPrintln() = println(showName())
}

class Student(val subName: String) : Person(subName) {
    private fun showName() {
        println("人的名字是：name $name")
    }

    override fun myPrintln() = println(showName())
}

// TODO 83.Kotlin语言的继承与重载的open关键字学习
fun main() {
    val p: Person = Student("张三")
    p.myPrintln()
}