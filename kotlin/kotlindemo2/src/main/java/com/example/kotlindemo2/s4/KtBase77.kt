package com.example.kotlindemo2.s4

// 第一步：生成val sex:Char
class KtBase77(_name: String, val sex: Char) { // 主构造函数

    // 第二步：生成val mName
    val mName = _name;

    init {
        val nameValue = _name; // 第三步：生成nameValue细节
        println("init代码块打印：nameValue:$nameValue")
    }

    constructor(name: String, sex: Char, age: Int) : this(name, sex) {
        // 第四步：生成次构造的细节
        println("次构造函数的三个参数，name:$name,sex:$sex,age:$age")
    }

}

// TODO 77.Kotlin语言的构造初始化顺序学习
// 1、main调用次构造
// 2、主构造的val变量
// 3、var mName = _name
// 4、init{}
fun main() {
    KtBase77("王五", '男', 88)
}