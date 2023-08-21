package com.example.kotlindemo2.s7

class MyObject {
    companion object {

        @JvmField
        val TARGET = "黄石公园"

        @JvmStatic
        fun showAction(name: String) = println("$name 要去 $TARGET 玩")
    }
}

// TODO 134.注解 @JvmStatic与 Kotlin关系
fun main() {
    println(MyObject.TARGET)
    MyObject.showAction("lisi")
}




