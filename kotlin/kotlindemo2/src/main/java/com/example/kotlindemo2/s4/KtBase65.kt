package com.example.kotlindemo2.s4

import java.io.File

// TODO 65.Kotlin语言的数组类型
/*
    Kotlin语言中的各种数组类型，虽然是引用类型，背后可以编译成Java基本数据类型
    ByteArray             byteArrayOf
    ShortArray            ShortArrayOf
    IntArray              intArrayOf
    LongArray             longArrayOf
    FloatArray            floatArrayOf
    DoubleArray           doubleArrayOf
    BooleanArray          booleanArrayOf
    Array                 arrayOf
 */

// 1、intArrayOf 常规操作的越界崩溃
// 2、elementAtOrElse  elementAtOrNull
// 3、List集合转数组
// 4、arrayOf Array<File>
fun main() {
    var intArray = intArrayOf(1, 2, 3, 4, 5)

    println(intArray[0])
    println(intArray[1])
    println(intArray[2])
    println(intArray[3])
    println(intArray[4])
    // println(intArray[5]) // 崩溃：会越界异常

    println()

    // 2、elementAtOrElse  elementAtOrNull
    println(intArray.elementAtOrElse(0) { -1 })
    println(intArray.elementAtOrElse(100) { -1 })

    println(intArray.elementAtOrNull(0))
    println(intArray.elementAtOrNull(200))

    // elementAtOrNull与空合并操作符一起使用
    println(intArray.elementAtOrNull(666) ?: "你越界啦")

    // 3、List集合转数组
    var charArray = listOf('A', 'B', 'C').toCharArray()

    // 4、arrayOf Array<File>
    val fileArray = arrayOf(File("aaa"), File("bbb"), File("ccc"))
}