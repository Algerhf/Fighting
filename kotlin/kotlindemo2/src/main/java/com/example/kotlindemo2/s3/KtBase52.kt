package com.example.kotlindemo2.s3

// TODO 52.Kotlin语言的 run 内置函数
// 1.run函数的特点，字符串延时
fun main(){
    val str = "Derry is OK"
    val r1 = str.run {
        // this == str
        true
    }
    println(r1)   // true

    // 下面是 具名函数 配合run函数

    // 2.具名函数判断长度  isLong

    // 这个是属于 匿名函数配合run
    str.run {
        // this == str本身
    }

    // 这个是属于具名函数
    // str.run(具名函数)
    str.run(::isLong)    // this == str本身
        .run(::showText) // this == isLong返回的boolean值
        .run(::mapText)  // this == showText返回的字符串
        .run(::println)

    // let函数持有it  run函数持有this 都可以很灵活的，把上一个结果值传给下一个函数
    str.let(::isLong)    // it == str本身
        .let(::showText) // it == isLong返回的boolean值
        .let(::mapText)  // it == showText返回的字符串
        .let(::println)

    // >>>>>>>>>>>>>>>上面全部都是具名函数调用给run执行，下面全是匿名函数调用给run执行
    str.run {
        str.length>5
    }.run {
        if(this) "你的字符串合格" else "你的字符串不合格"
    }.run {
        "【$this】"
    }

}


fun isLong(str:String) = str.length>5

fun showText(isLong:Boolean) =  if(isLong) "你的字符串合格" else "你的字符串不合格"

fun mapText(getShow:String) = "【$getShow】"
