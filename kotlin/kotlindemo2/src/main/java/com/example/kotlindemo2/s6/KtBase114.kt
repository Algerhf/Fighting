package com.example.kotlindemo2.s6

data class ResponseResult(val msg:String,val code:Int)

fun Any.showPrintlnContent() = println("当前内容是：$this")

// TODO 114.Kotlin语言的超类上定义扩展函数
// 1、扩展函数不允许被重复定义
// 2、对超类扩展函数的影响
// 3、扩展函数  链式调用
fun main() {
    ResponseResult("login success",200).showPrintlnContent()
}

