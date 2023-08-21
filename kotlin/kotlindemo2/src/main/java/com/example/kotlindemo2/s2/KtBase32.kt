 package com.example.kotlindemo2.s2

// TODO 32.Kotlin语言的函数引用学习
fun main() {
    // 函数引用
    // lambda属于函数类型的对象,需要把methodResponseResult普通函数变成 函数类型的对象（函数引用）
    login("Derry", "123456", ::methodResponseResult)
}

fun methodResponseResult(msg: String, code: Int) {
    println("最终登录的情况如下：msg:$msg code:$code")
}


const val USER_NAME_SAVE_DB4 = "Derry"
const val USER_PWD_SAVE_DB4 = "123456"

inline fun login(name: String, pwd: String, responseResult: (String, Int) -> Unit) {
    if (USER_NAME_SAVE_DB4 == name && USER_PWD_SAVE_DB4 == pwd) {
        responseResult("登录成功", 200)
    } else {
        responseResult("登录失败", 444)
    }
}
