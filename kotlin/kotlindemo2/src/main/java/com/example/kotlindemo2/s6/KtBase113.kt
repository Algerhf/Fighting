package com.example.kotlindemo2.s6

// 假设这个代码是开源的，或者是很庞大的JDK源码，或者是非常复杂的开源库
class KtBase113(val name: String, val age: Int, val sex: Char)

// 增加扩展函数
fun KtBase113.show() {
    println("我是show函数，name:$name age:$age")
}

fun String.addExtAction(count: Int) = this + "@".repeat(count)

// TODO 113.Kotlin语言的定义扩展函数
fun main() {
    val p = KtBase113("张三", 18, '男')
    p.show()

    println("Derry".addExtAction(8))
}

