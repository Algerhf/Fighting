package com.example.kotlindemo2.s1

// TODO 15.kotlin语言的String模板
fun main() {
    val garden = "黄石公园"
    val time = 6

    // 想打印$符号本身的话，需要使用 ${'$'}
    println("今天天气很晴朗，去${garden}玩，玩了$time 小时  花了${'$'}20")

    // java的if是语句，有局限性
    // KT的if是表达式，所以可以更灵活
    val isLogin = true
    println("server response result:${if (isLogin) "登录成功" else "登录失败"}")
}