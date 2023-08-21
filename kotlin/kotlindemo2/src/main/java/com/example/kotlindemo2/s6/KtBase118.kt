package com.example.kotlindemo2.s6

fun String?.outputStringValueFun(default: String) = println(this ?: default)

// TODO 118.Kotlin语言的可空类型扩展函数
fun main() {

    // infoValue是可空类型   String? == 可空类型
    val infoValue: String? = null
    infoValue.outputStringValueFun("default")

    val name = "Derry"
    name.outputStringValueFun("default")
}
