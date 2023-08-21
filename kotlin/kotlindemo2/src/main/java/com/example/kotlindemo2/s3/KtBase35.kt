package com.example.kotlindemo2.s3

// TODO 35.Kotlin语言的可空性特点
fun main(){

    // TODO 第一种情况： 默认是不可空类型，不能随意给null
    var name:String = "Derry"

    // 提示：  不能是非空类型String的值
    // name = null

    println(name)

    // TODO 第二种情况：声明时指定为可空类型
    var name2:String?
    name2 = null
    // name2 = "Derry"
    println(name2)
}