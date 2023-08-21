package com.example.kotlindemo2.s1

// TODO 17.kotlin中函数的默认参数
fun main() {
    action01("lisi",89)

    action02("wangwu") // 可以不传，使用默认值
    action02("wangwu",88) // 也可以传，会覆盖默认值

    action03()
}

private fun action01(name: String,age: Int){
    println("你的姓名是：$name 你的年龄是$age")
}

// 给函数的参数 指定默认值
private fun action02(name: String,age: Int = 77){
    println("你的姓名是：$name 你的年龄是$age")
}

private fun action03(name: String = "王五",age: Int = 77){
    println("你的姓名是：$name 你的年龄是$age")
}