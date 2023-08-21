package com.example.kotlindemo2.s5

// 普通类
class RespondResultBean1(var msg: String, var code: Int, var data: String)
// set get

// 数据类
data class RespondResultBean2(var msg: String, var code: Int, var data: String)
// set get 构造函数  解构操作 copy toString equals hashCode

// TODO 91.Kotlin语言的数据类学习
/**
 * 1、数据类
 * JVM想要实现无参构造，必须给主构造函数的属性指定默认初始化值
 *
 * 1、主构造函数至少有一个参数
 * 2、主构造函数里的属性必须使用var或val标识
 * 3、数据类不能是  抽象abstract 继承open  密封sealed  内部inner
 * 4、数据类不能继承其他类，但可以实现接口
 *
 */
fun main() {
    println(RespondResultBean1("loginSuccess", 200, "数据"))

    println(RespondResultBean2("loginSuccess", 200, "数据"))

    println(
        RespondResultBean1("loginSuccess", 200, "数据") ==
                RespondResultBean1("loginSuccess", 200, "数据")
    )//false

    println(
        RespondResultBean2("loginSuccess", 200, "数据") ==
                RespondResultBean2("loginSuccess", 200, "数据")
    )//true
}

