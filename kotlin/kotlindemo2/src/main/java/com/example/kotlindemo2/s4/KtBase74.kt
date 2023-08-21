package com.example.kotlindemo2.s4

class KtBase74(name: String) { // 主构造函数

    // 次构造函数，必须要调用主构造函数，否则不通过
    // 为什么次构造函数必须调用主构造？答：主构造统一管理，为了更好的初始化设计
    constructor(name: String, sex: Char) : this(name) {

    }

    // 三个参数的次构造函数，必须调用主构造函数
    constructor(name: String, sex: Char, age: Int) : this(name) {
        println("3个参数的次构造函数")
    }

    // 四个参数的次构造函数，必须调用主构造函数
    constructor(name: String, sex: Char, age: Int, info: String) : this(name) {
        println("4个参数的次构造函数")
    }
}

// TODO 74.Kotlin语言的次构造函数学习
fun main() {
    val p = KtBase74("张三", 'M')

    KtBase74("张三", 'M')
    KtBase74("张三", 'M', 70)
    KtBase74("张三", 'M', 78, "KT")


}