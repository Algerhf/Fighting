package com.example.kotlindemo2.s4

class KtBase76(username: String, age: Int, sex: Char) { // 主构造函数

    // 这个不是java静态代码块
    // 相当于java的{} 构造代码块
    // 初始化代码块，init代码块
    init {
        println("主构造函数被调用了")

        // 如果第一个参数为false,就会调用第二个参数的lambda
        require(username.isNotBlank()) {
            "您的姓名为空，异常抛出"
        }

        require(age > 0) {
            "您的年龄不合法，异常抛出"
        }
    }

    constructor(username: String) : this(username, 87, 'M') {
        println("次构造函数被调用了")
    }

}

// TODO 76.Kotlin语言的初始化块学习
fun main() {
    // KtBase76("Wang",87,'M')

    KtBase76("王五")
}