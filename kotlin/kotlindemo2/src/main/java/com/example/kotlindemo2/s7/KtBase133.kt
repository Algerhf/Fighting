package com.example.kotlindemo2.s7

fun show(name:String,age:Int=20,sex:Char='M'){
    println("name:$name,age:$age,sex:$sex")
}

// 默认参数
@JvmOverloads  // 原理：编译器环节 专门重载一个函数
fun toast(name:String,sex:Char='M'){
    println("name:$name,sex:$sex")
}

// TODO 133.注解 @JvmOverloads与 Kotlin
fun main() {

}




