package com.example.kotlindemo2.s7

import kotlin.properties.Delegates

class KtBase135 {

    var address by Delegates.vetoable(""){init,old,new ->
        true
    }

    var name: String by Delegates.notNull()

    val age: Int by Delegates.observable(10) { init, old, new ->

    }

    val sex:Char by lazy {
        'A'
    }
}

class Size(val map: MutableMap<String, Int>) {
    val name by map
    val age by map
}

// TODO Kotlin语言的委托学习
fun main() {
    val size = Size(mutableMapOf("张三" to 20))
    println(size.name)
    println(size.age)
}