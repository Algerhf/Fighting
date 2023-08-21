package com.example.kotlindemo2.s4

class KtBase78 { // 主构造函数

    lateinit var responseResultInfo: String  // 等会再来初始化，先定义再说

    fun request() {
        responseResultInfo = "服务器加载成功"
    }

    fun showResponseResult() {
        // 由于没有初始化，属于懒加载，用到就会初始化
        // if(responseResultInfo==null) println()
        // println("responseResultInfo:$responseResultInfo")

        if (::responseResultInfo.isInitialized) {
            println("responseResultInfo:$responseResultInfo")
        } else {
            println("您还没有初始化")
        }
    }

}

// TODO 78.Kotlin语言的延迟初始化lateinit学习
fun main() {
    val p = KtBase78()

    // 使用之前要加载一下，属于懒加载
    p.request()

    // 使用
    p.showResponseResult()
}