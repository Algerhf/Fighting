package com.example.kotlindemo2.s5

interface RunnableKt {
    fun run()
}

open class KtBase88 {

    open fun add(info: String) = println("KtBase88 add：$info")

    open fun del(info: String) = println("KtBase88 del：$info")

}

// TODO 88.Kotlin语言的对象表达式学习
fun main() {




    // 具名实现方式
    val p2 = KtBase88Impl()
    p2.add("刘一")
    p2.del("刘二")

    // 对java接口用 对象表达式方式  方式一
    val p3 = object : Runnable {
        override fun run() {
            println("Runnable run...")
        }
    }
    p3.run()

    // 对java接口用  java最简洁的方式  方式二
    val p4 = Runnable {
        "Runnable run2..."
    }
    p4.run()

    // 对Kotlin接口用 kotlin对象表达式的方式  方式一
    object : RunnableKt {
        override fun run() {
            "RunnableKT方式一 running..."
        }
    }.run()

    // 对Kotlin接口用   java最简洁的方式  方式二  无法使用
/*    RunnableKt{

    }*/

    // 小结：java接口有两种方式  1、（object:对象表达式）  2、简洁版
    //       KT接口只有一种方式  1、（object:对象表达式）
}

class KtBase88Impl : KtBase88() {
    override fun add(info: String) {
        super.add(info)
        println("我是具名对象  add：$info")
    }

    override fun del(info: String) {
        super.del(info)
        println("我是具名对象 del：$info")
    }
}