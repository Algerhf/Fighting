package com.example.kotlindemo2.s5

// TODO 内部类
// 内部类的特点：内部类和外部类可以互相访问
class Body(_bodyInfo: String) {
    val bodyInfo = _bodyInfo

    // KT中，内部的类不能访问外部的类，要增加inner修饰符才能成为内部类
    inner class Heart {
        fun run() = println("心脏访问身体信息：$bodyInfo")
    }
}

// TODO 嵌套类
// 默认情况下：就是嵌套类
class Outer {
    val info: String = "OK"

    class Nested {
        fun output() = println("嵌套类")
    }
}

// TODO 90.Kotlin语言的嵌套类
fun main() {
    // 内部类调用
    Body("身体信息").Heart().run()

    // 嵌套类调用
    Outer.Nested().output()
}

