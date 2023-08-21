package com.example.kotlindemo2.s6

class KtBase104<T>(private val isR: Boolean, private val obj: T) {
    fun show() = println("万能输出器：$obj")
}

fun <T> show(item: T) {
    item?.apply {
        println(this)
    } ?: println("为null")
}

// TODO 104.Kotlin语言的泛型函数
fun main() {
    val stu1 = Stud("张三", 18, '男')
    val stu2 = Stud("李四", 19, '女')

    val tea1 = Stud("王五", 20, '男')
    val tea2 = Stud("赵六", 21, '女')

    show(stu1)
    show(stu2)
    show(tea1)
    show(tea2)

    show("zhang")
    show(100)
    show(3.14f)
}

