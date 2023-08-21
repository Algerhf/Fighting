package com.example.kotlindemo2.s6

import java.io.File

// TODO 122.Kotlin语言的apply函数详解
fun main() {
    File("D:\\a.txt")
        .mApply {
            setReadable(true)
        }

    "".apply {

    }


}

private inline fun <T> T.mApply(lambda: T.() -> Unit): T {
    lambda()
    return this
}
