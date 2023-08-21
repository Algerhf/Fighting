package com.example.kotlindemo2.s2

// TODO 33.Kotlin语言的函数类型作为返回类型
fun main() {
    // 返回值类型为：Boolean
    val r = show("学习Kotlin语言")

    // 返回值类型为：String
    val r2 = show2("学习Kotlin语言")

    // result 是showMethod函数的返回值，只不过返回值是一个函数
    val result = showMethod("lisi")
    println(result("zhangsan", 18))
}

fun show(info: String): Boolean {
    println("我是show函数 info:$info")
    return true;
}

fun show2(info: String): String {
    println("我是show2函数 info:$info")
    return "ddd";
}

fun showMethod(info: String): (String, Int) -> String {
    println("我是showMethod函数 info:$info")
    return { name: String, age: Int ->
        "hello"
    }
}
