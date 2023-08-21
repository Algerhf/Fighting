package com.example.kotlindemo2.s6

class KtBase108<INPUT>(vararg objects: INPUT, val isR: Boolean = true) {

    val objectArray: Array<out INPUT> = objects

    // 运算符重载
    operator fun get(index: Int) = objectArray[index].takeIf { isR }
}

// TODO 108.Kotlin语言的 [ ] 操作符
fun main() {
    val p = KtBase108("张三", "李四", "王五")
    println(p[0])
    println(p[1])

}

