package com.example.kotlindemo

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun main(args: Array<String>) {
//    var value = Color.valueOf(Color.RED.name)
//    println(value.rgb)

    printAllValues<RGB>()

    val size = object {
        var name = ""
        var age = 18
    }

    println(size.name)
    println(size.age)
}

enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    println(enumValues<T>().joinToString { it.name })

    println(enumValueOf<T>("RED"))
}