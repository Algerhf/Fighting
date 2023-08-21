package com.example.kotlindemo


open class A(x: Int) {
    open val y = x
}

interface B {

}

// 对象声明可以实现一个单例
class TestObject {
    val name = "菜鸟教程"

    object DeskTop {
        val url = "www.baidu.com"

        fun showName() {
            // 对象声明作为内部类时，不能访问外部类变量
//             println("name = $name")
        }
    }
}

/**
 * 伴生对象
 */
class TestCompanion {
    companion object {
        fun create() = TestCompanion()
    }
}

/**
 * companion关键字在一个类中只能使用一次
 */
interface Factory<T> {
    fun create(): T
}

class TestCompanion2 {
    companion object : Factory<TestCompanion2> {
        override fun create() = TestCompanion2()
    }
}

fun main(args: Array<String>) {
/*  // 通过对象表达式实现一个匿名内部类的对象用于方法的参数中
    // 对象可以继承超类或接口，多个超类或接口使用逗号隔开，如果超类有构造函数，需要传递参数给构造函数
    val ab: A = object : A(1), B {
        override val y = 15
    }
    println(ab.y)

    // 对象表达式可以越过类的定义
    val site = object {
        val name = "菜鸟教程"
        val url = "www.runboo.com"
    }
    println(site.name)
    println(site.url)

    val testObject = TestObject()
    // 无法访问
    // var url = testObject.DeskTop.url
    val url = TestObject.DeskTop.url
    println("对象声明作为内部类时 url = $url")*/

    // 伴生对象的内部属性可以通过类名直接访问
    var testCom = TestCompanion.create()



}