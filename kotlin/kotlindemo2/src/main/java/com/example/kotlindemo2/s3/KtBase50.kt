package com.example.kotlindemo2.s3

import java.io.File

// TODO 50.Kotlin语言的apply内置函数
fun main() {
    val info = "Derry you hao"

    // 普通方式
    println("info字符串的长度是:${info.length}")
    println("info最后一个字符是：${info[info.length - 1]}")
    println("info全部转换成小写是：${info.toLowerCase()}")

    // apply内置函数的方式
    info.apply {
        println("info字符串的长度是:$length")
        println("info最后一个字符是：${this[length - 1]}")
        println("info全部转换成小写是：${toLowerCase()}")
    }

    // 真正使用apply函数的写法规则如下：
    // info.apply特点：apply函数始终是返回 ”info本身“，所以可以链式调用
    info.apply {
        println("长度是：$length")
    }.apply {
        println("最后一个字符是：${this[length - 1]}")
    }.apply {
        println("全部转成小写是：${toLowerCase()}")
    }

    // 普通写法
    var file = File("D:\\a.txt")
    file.setExecutable(true)
    file.setReadable(true)
    println(file.readLines())

    // apply写法
    // 匿名函数里面  持有的this == file本身
    file.apply {
        setExecutable(true)
    }.apply {
        setReadable(true)
    }.apply {
        println(readLines())
    }
}
