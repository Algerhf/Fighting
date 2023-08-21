package com.example.kotlindemo2.s3

const val INFO = "Derry is Success Result"

// TODO 42.Kotlin语言的substring
fun main() {
    val indexof = INFO.indexOf("i")
    println(INFO.substring(0, indexof))
    println(INFO.substring(0 until indexof)) // KT基本上用此方式：0 until indexof
}
