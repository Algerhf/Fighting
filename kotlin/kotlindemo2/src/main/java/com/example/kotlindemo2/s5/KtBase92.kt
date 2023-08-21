package com.example.kotlindemo2.s5

data class KtBase92(var name: String, var age: Int) {

    var coreInfo: String = ""

    init {
        println("主构造函数执行了")
    }

    constructor(name: String) : this(name, 18) {
        println("次构造函数执行了")
        coreInfo = "增加核心代码"
    }

}

// TODO 92.Kotlin语言的Copy函数学习
fun main() {
    // copy equals toString 只管主构造函数，不管次构造
    val p1 = KtBase92("张三", 20)
    println(p1)

    val p2 = p1.copy("李四", 30)
    println(p2)

}

