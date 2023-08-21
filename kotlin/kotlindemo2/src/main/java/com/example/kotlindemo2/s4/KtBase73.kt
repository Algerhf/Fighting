package com.example.kotlindemo2.s4

// 一步到位：在主构造函数里，给参数添加var或val修饰符
class KtBase73(var name: String, val sex: Char, val age: Int, var info: String) { // 主构造函数

    fun show() {
        println(name)
        println(sex)
        println(age)
        println(info)
    }
}

// TODO 73.Kotlin语言的主构造函数里定义属性
fun main() {
    val p = KtBase73(name = "zhang", info = "KT", age = 20, sex = 'm')
    println(p.name)
    // p.name = "AAA"  // set函数被私有化了
    p.show()

}