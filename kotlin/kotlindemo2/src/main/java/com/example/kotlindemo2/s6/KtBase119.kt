package com.example.kotlindemo2.s6

// 自定义中缀表达式
// 1、条件1：对第一个对象的函数扩展
// 2、条件2：需要传递一个参数
private infix fun <C1, C2> C1.go(c2: C2) = 111

// TODO 119.Kotlin语言的infix关键字
// infix == 中缀表达式，可以简化代码
fun main() {
    mapOf("" to 1)

    var p = 123 go 345
}
