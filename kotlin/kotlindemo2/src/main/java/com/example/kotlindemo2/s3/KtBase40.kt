package com.example.kotlindemo2.s3

// TODO 40.Kotlin空合并操作符
fun main() {

    var info: String? = "李小龙"
    info = null

    // 空合并操作符   xxx?:"原来你是null啊"      // 如果xxx等于null,才会执行?:后面的区域
    println(info ?: "原来你是null啊")

    // let + 空合并操作符
    println(info?.let { "[$it]" } ?: "原来你是null啊")
}