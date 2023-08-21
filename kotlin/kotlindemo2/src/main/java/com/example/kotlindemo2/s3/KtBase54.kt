package com.example.kotlindemo2.s3

import java.io.File

// TODO 54.Kotlin语言的 also内置函数
fun main(){
    val str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    str.also{
        // it == str本身
    }

    // 真正使用also函数的写法规则如下：
    // str.also 特点：also函数始终是返回 ”str本身“，所以可以链式调用
    str.also{
        println("str原始数据是：$it")
    }.also{
        println("str转换成小写的效果是：${it.toLowerCase()}")
    }.also{
        println("结束了")
    }

    // 普通写法
    var sourceFile = File("D:\\a.txt")
    sourceFile.setExecutable(true)
    sourceFile.setReadable(true)
    println(sourceFile.readLines())

    // also写法
    // 匿名函数里面  持有的it == file本身
    sourceFile.also {
        it.setExecutable(true)
    }.also {
        it.setReadable(true)
    }.also {
        println(it.readLines())
    }

    // sourceFile没有任何影响

}


