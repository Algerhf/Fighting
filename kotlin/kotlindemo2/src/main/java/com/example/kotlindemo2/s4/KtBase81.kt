package com.example.kotlindemo2.s4

class KtBase81 {

    val info: String

    init {
        getInfoMethod()
        info = "DerryOK"
    }

    private fun getInfoMethod() {
        println("info:${info[0]}")
    }

    val number = 9

}

// TODO 81.Kotlin语言的初始化陷阱二学习
fun main() {
    KtBase81()
}