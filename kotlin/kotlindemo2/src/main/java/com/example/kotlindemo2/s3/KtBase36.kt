package com.example.kotlindemo2.s3

// TODO 36.Kotlin语言的安全调用操作符
fun main(){

    var name:String? = "zhangsan"
    name = null

    // name.capitalize()  // name是可空类型，可能是null,想要使用name，必须给出补救措施
    var r = name?.capitalize()  // name是可空类型，如果真的是null,?后面的这一段代码不执行，就不会引发空指针异常
    println(r) // null
}