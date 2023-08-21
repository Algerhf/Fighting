package com.example.kotlindemo2.s3

// TODO 39.Kotlin语法中对比使用if判断null值的情况
fun main() {

    var name: String? = "zhangsan"

    // name.capitalize()  // name是可空类型，可能是null,想要使用name，必须给出补救措施

    if (name != null) { // if也算是补救措施
        val r = name.capitalize()
        println(r)
    } else {
        println("name is null")
    }
}