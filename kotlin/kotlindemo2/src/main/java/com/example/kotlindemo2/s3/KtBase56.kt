 package com.example.kotlindemo2.s3

// TODO 56.Kotlin语言的 takeUnless内置函数
// takeIf 和 takeUnless 功能是相反的
fun main(){

    // name.takeIf{ true/false}
    // true：直接返回name
    // false：直接返回null

    // name.takeUnless{ true/false}
    // true：直接返回null
    // false：直接返回name

    // 小结  takeUnless + it.isNullOrBlank()一起使用，可以验证字符串有没有初始化等功能
//    var r = Manager(null).infoValue.takeUnless {
//        it.isNullOrBlank()
//    } ?: "未经过任何初始化值"
//
//    println(r)
}

data class Manager(var infoValue:String?)



