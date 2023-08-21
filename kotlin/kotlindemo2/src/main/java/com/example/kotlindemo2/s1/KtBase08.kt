package com.example.kotlindemo2.s1

// TODO 08.Kotlin语言的只读变量
fun main() {

    // 默认提示：变量永远不会被修改，建议修改成val == 不可改变的（只读）
    val info :String = "MyInfo"
    println(info)

    /*
       只读     变量名   类型   值
       val     age   ： Int = 99
    */
    val age:Int = 99
    // age = 98 // 不能修改

    // 可读
    println(age)
}