package com.example.kotlindemo2.s2

// TODO 30.Kotlin语言的简略写法学习
fun main() {

    // 第一种方式
    loginAPI2("Derry", "123456", { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    })

    // 第二种方式
    loginAPI2("Derry", "123456", responseResult = { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    })

    // 第三种方式
    loginAPI2("Derry", "123456") { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    }
}

// 模拟：数据库SQLServer
const val USER_NAME_SAVE_DB2 = "Derry"
const val USER_PWD_SAVE_DB2 = "123456"

// 登录API 模仿前端
public fun loginAPI2(username: String?, password: String?, responseResult: (String, Int) -> Unit) {
    if (username == null || password == null) {
        TODO("用户名或密码为null") // 出现问题，终止程序
    }

    if (username.length > 3 && password.length > 3) {
        if (webServiceLoginAPI2(username, password)) {
            // 登录成功
            // 做很多事情，校验成功信息等
            // ...
            responseResult("login success", 200)
        } else {
            // 登录失败
            responseResult("login failed", 444)
        }
    } else {
        TODO("用户名和密码不合格")
    }
}

// 登录的API 服务器
private fun webServiceLoginAPI2(name: String, pwd: String): Boolean {
    return name == USER_NAME_SAVE_DB2 && pwd == USER_PWD_SAVE_DB2
}