package com.example.kotlindemo2.s2

// TODO 26.kotlin语言的it关键字特点
fun main() {
    val methodAction: (Int, Int, Int) -> String = { n1, n2, n3 ->
        val number = 24364
        println("$number Derry n1：$n1,n2：$n2,n3：$n3")
        "$number Derry n1：$n1,n2：$n2,n3：$n3"
    }

    // methodAction.invoke(1,2,3)
    methodAction(1, 2, 3)

    // 匿名函数只有一个参数时，默认有一个it变量代表该参数
    val methodAction2: (String) -> String = { "$it Derry" }
    println(methodAction2("DDD"))

    val methodAction3: (Double) -> String = { "$it Derry" }
    println(methodAction3(123.45))
}

/*
fun methodAction2(it:String):String { return "$it Derry"}
*/



