 package com.example.kotlindemo2.s2

// TODO 34.Kotlin语言的匿名函数与具名函数
fun main() {

    // 匿名函数
    showPersonInfo("lisi", 99, '男', "学习KT语言") {
        println("显示结果：$it")
    }

    // 具名函数
    showPersonInfo("wangwu", 89, '女', "学习C++", ::showResultImpl)
}

fun showResultImpl(result: String) {
    println("显示结果：$result")
}

inline fun showPersonInfo(
    name: String,
    age: Int,
    sex: Char,
    study: String,
    showResult: (String) -> Unit
) {
    val str = "name:$name, age:$age, sex:$sex study:$study"
    showResult(str)
}

