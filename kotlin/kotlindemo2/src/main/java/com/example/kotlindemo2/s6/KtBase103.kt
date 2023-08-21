package com.example.kotlindemo2.s6

class KtBase103<T>(private val obj: T) {
    fun show() = println("万能输出器：$obj")
}

data class Stud(val name: String, val age: Int, val sex: Char)
data class Tea(val name: String, val age: Int, val sex: Char)

// TODO 103.Kotlin语言的定义泛型类
fun main() {
    val stu1 = Stud("张三", 18, '男')
    val stu2 = Stud("李四", 19, '女')

    val tea1 = Stud("王五", 20, '男')
    val tea2 = Stud("赵六", 21, '女')

    KtBase103(stu1).show()
    KtBase103(stu2).show()
    KtBase103(tea1).show()
    KtBase103(tea2).show()

    KtBase103("字符串").show()
    KtBase103(100).show()
    KtBase103(3.14).show()
}

