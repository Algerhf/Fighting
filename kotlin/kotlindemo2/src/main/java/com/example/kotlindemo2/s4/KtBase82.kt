package com.example.kotlindemo2.s4

class KtBase82(_info: String) {

    val content = getInfoMethod()

    val info = _info

    private fun getInfoMethod() = info

}

// TODO 82.Kotlin语言的初始化陷阱三学习
fun main() {
    println("内容的长度是: ${KtBase82("Derry").content.length}")
}