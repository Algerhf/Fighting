package com.example.kotlindemo2.s1

// TODO 19.kotlin语言的Unit函数特点
fun main() {
    doWork()
    doWork2()
    doWork3()
}

// java语言的void关键字（void是无参数返回的 忽略类型）  但是它是关键字，不是类型，这很矛盾
// Unit不写，默认也有  Unit代表无参数返回的忽略类型 == Unit类型类
private fun doWork():Unit{
    return println()
}

private fun doWork2(){
    return println()
}

private fun doWork3()/*:Unit*/{
    println()
}
