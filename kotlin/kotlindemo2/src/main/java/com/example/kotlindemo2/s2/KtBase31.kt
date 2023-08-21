package com.example.kotlindemo2.s2

// TODO 31.Kotlin语言的简略写法学习
fun main() {

    // 第一种方式
    loginAPI3("Derry", "123456", { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    })

    // 第二种方式
    loginAPI3("Derry", "123456", responseResult = { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    })

    // 第三种方式
    loginAPI3("Derry", "123456") { msg: String, code: Int ->
        println("最终登录的情况如下：msg:$msg code:$code")
    }
}

// 模拟：数据库SQLServer
const val USER_NAME_SAVE_DB3 = "Derry"
const val USER_PWD_SAVE_DB3 = "123456"

// 此函数有使用lambda作为参数，就需要声明为内联
// 如果此函数不使用内联，在调用端，会生成多个对象来lambda的调用（会造成性能损耗）
// 如果此函数使用内联，相当于C++ #define 宏定义 宏替换
// 小结：如果函数参数有lambda，尽量使用inline关键字，这样内部会做优化，减少函数开辟 对象开辟 的损耗
// 登录API 模仿前端
inline fun loginAPI3(username: String?, password: String?, responseResult: (String, Int) -> Unit) {

    if (username == null || password == null) {
        TODO("用户名或密码为null") // 出现问题，终止程序
    }

    if (username.length > 3 && password.length > 3) {
        if (webServiceLoginAPI3(username, password)) {
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

// 此函数没有使用lambda作为参数，不需要声明为内联
// 登录的API 服务器
fun webServiceLoginAPI3(name: String, pwd: String): Boolean {
    return name == USER_NAME_SAVE_DB3 && pwd == USER_PWD_SAVE_DB3
}