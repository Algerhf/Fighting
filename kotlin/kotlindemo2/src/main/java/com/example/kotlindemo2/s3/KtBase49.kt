package com.example.kotlindemo2.s3

import kotlin.math.roundToInt

// TODO 49.Kotlin语言中 Double转 Int与类型格式化
fun main() {

    println(65.4645654.toInt())  // 65 四舍五入

    println(65.4645654.roundToInt())  // 65  四舍五入

    println(65.8645654.roundToInt())  // 66  四舍五入

    // 结论：用 roundToInt() 函数，保证Double -> Int 持有四舍五入的效果

    val r: String = "%.3f".format(65.8645633)
    println(r)
}
