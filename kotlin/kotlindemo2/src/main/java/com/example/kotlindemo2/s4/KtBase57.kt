package com.example.kotlindemo2.s4

// TODO 57.Kotlin语言的List创建于元素获取学习
// 普通取值方式： 索引
// 防止崩溃取值方式： getOrElse   getOrNull
fun main() {
    val list = listOf("Derry", "zs", "ls", "ww")

    // 普通取值方式  索引   内部是运算符重载 [] = get
    println(list[0])
    println(list[1])
    println(list[2])
    println(list[3])
    // println(list[4]) // 奔溃
    println()

    // 我们写KT代码，一定不会再出现空指针异常，小标越界异常
    // 防止奔溃取值方式：getOrElse  getOrNull
    println(list.getOrElse(3) { "越界" })
    println(list.getOrElse(4) { "越界" })

    println("-----------")

    println(list.getOrNull(3))
    println(list.getOrNull(4) ?: "你越界了哦")

    // 小结：开发过程中，尽量使用getOrElse或getOrNull，才能体现KT的亮点

}