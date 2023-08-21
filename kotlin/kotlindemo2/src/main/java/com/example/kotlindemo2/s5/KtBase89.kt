package com.example.kotlindemo2.s5

class KtBase89 {
    companion object {
        val info = "DerryInfo"
        fun showInfo() = println("显示：$info")
    }
}

// TODO 89.Kotlin语言的伴生对象学习
// 伴生对象的由来，在KT中没有java的这种static静态，伴生很大程度上和java的这种static静态差不多
// 伴生对象只会加载一次,只会初始化一次
fun main() {
    // 背后代码：System.out.println(KtBase89.Companion.getInfo())
    println(KtBase89.info)

    // 背后代码：System.out.println(KtBase89.Companion.showInfo())
    KtBase89.showInfo()

    KtBase89()
    KtBase89()
    KtBase89()
    KtBase89()
    KtBase89()
}

