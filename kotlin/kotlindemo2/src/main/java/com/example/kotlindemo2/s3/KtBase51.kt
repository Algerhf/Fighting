package com.example.kotlindemo2.s3

/**
 * 内置函数的总结:
 *
 * apply:
 * 1.apply函数返回类型，永远都是info本身
 * 2.apply函数的匿名函数里面持有的是 this == info本身
 *
 * let:
 * 1.let函数返回类型，是根据匿名函数最后一行的变化而变化
 * 2.let函数的匿名函数里面持有的是it == 集合本身
 *
 */


// TODO 51.Kotlin语言的let内置函数
fun main(){

    // 普通方式，对集合的第一个元素相加
    val list = listOf(6,5,2,3,5,7)
    val first = list.first()
    val result = first+first
    println(result)

    // let方式  对集合的第一个元素相加
    // let函数返回类型，是根据匿名函数最后一行的变化而变化
    // let函数的匿名函数里面持有的是it == 集合本身
    val res2 = listOf(6, 5, 2, 3, 5, 7).let {
        list.first() + list.first() // let的特点：匿名函数的最后一行作为返回值
        // apply的特点：永远返回该对象info本身
    }
    println(res2)

    println()

    // 普通方式
    //println(getMethod1(null))

    println(getMethod3("hello"))
}

fun getMethod1(value:String?):String{
    return if(value == null) "你传递的是null" else "欢迎：$value"
}

// 简化方式
fun getMethod2(value:String?) = if(value == null) "你传递的是null" else "欢迎：$value"

// let方式 + 空合并操作符  对值盼null 并返回
fun getMethod3(value:String?):String{
    return value?.let {
        "欢迎回来 $it"
    }?:"你传递的是null"
}

fun getMethod4(value:String?) = value?.let { "欢迎回来 $it" }?:"你传递的是null"
