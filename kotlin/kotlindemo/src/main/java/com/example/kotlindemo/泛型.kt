package com.example.kotlindemo

import java.util.*

class Box<T>(t: T) {
    val value = t


}

fun main(args: Array<String>) {
    val intBox = Box(10)
    val stringBox = Box("hello")

    println(intBox.value)
    println(stringBox.value)


}

class Generic<out T>(val t: T) {
    fun foo(): T {
        return t
    }
}

class Generic2<in T>(t: T) {
    fun foo(t: T) {

    }
}

class FOOO<out T>(t: T) {

}

