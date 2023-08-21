package com.example.kotlindemo2.s1

const val PI3 = 3.1415 // 定义编译时常量

// TODO 10.kotlin语言的编译时常量
// 编译时常量只能是常用的基本数据类型：(Byte、Short、Int、Long、Float、Double、Boolean、Char、String)
// 编译时常量只能在函数之外定义，为什么?
//    答：如果在函数之内定义，就必须在运行时才能调用函数进行赋值，何来编译时常量一说

// 结论：编译时常量只能在函数之外定义，就可以在编译期间初始化
fun main() {

    var a = ""
    // 提示：修饰符const不适用于 局部变量
    // const val PI2 = 3.14

}