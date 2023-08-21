package com.example.kotlindemo2.s4

class KtBase75(name: String = "李元霸") { // 主构造函数

    // 2个参数的次构造函数，必须要调用主构造函数
    constructor(name: String = "李连杰", sex: Char = 'M') : this(name) {

    }

    // 三个参数的次构造函数，必须调用主构造函数
    constructor(name: String = "李小龙", sex: Char = 'M', age: Int = 20) : this(name) {
        println("3个参数的次构造函数")
    }

    // 四个参数的次构造函数，必须调用主构造函数
    constructor(name: String = "胡歌", sex: Char = 'w', age: Int = 18, info: String = "KT") : this(
        name
    ) {
        println("4个参数的次构造函数")
    }
}

// TODO 75.Kotlin语言的构造函数默认参数学习
fun main() {
    val p = KtBase75("李元霸2")

    KtBase75("张三", 'M')
    KtBase75("张三", 'M', 70)
    KtBase75("张三", 'M', 78, "KT")

    KtBase75() // 优先调用主构造函数
}