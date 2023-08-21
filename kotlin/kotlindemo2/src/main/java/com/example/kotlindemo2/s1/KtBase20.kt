package com.example.kotlindemo2.s1

// TODO 20.Kotlin语言的Nothing类型特点
fun main() {
    show(99)
}

private fun show(number:Int){
    when(number){
        -1 -> TODO("没有这种分数") // 会抛异常
        in 0..59 -> println("分数不及格")
        in 60..70 -> println("分数及格")
        in 71..100 -> println("分数优秀")
    }
}

interface A{
    fun show()
}

class AImpl :A{
    override fun show() {
        // 下面这句话，不是注释提示，会终止程序的
        TODO("Not yet implemented")
    }
}