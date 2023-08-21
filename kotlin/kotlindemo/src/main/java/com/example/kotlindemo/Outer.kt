package com.example.kotlindemo

class Outer {

    private val bar = 1
    var v = "成员属性"

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun foo() = bar
        fun innerTest() {
            val o = this@Outer
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}

fun main(args: Array<String>) {

    val demo = Outer().Inner().foo()
    println(demo)

    val demo2 = Outer().Inner().innerTest()
    println(demo2)
}