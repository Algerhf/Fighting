package com.example.kotlindemo2.s4

// TODO 64.Kotlin语言的集合转换与快捷函数学习
// 1、定义可变list集合
// 2、List转Set去重
// 3、List转Set 转List 也能去重
// 4、快捷函数去重  distinct
fun main() {

    val list = mutableListOf("Derry", "Derry", "Leo", "Lance")  // list可以有重复元素
    println(list)

    val set = list.toSet()
    println(set)

    val list2 = set.toMutableList()
    println(list2)

    // 快捷函数去重  distinct
    println(list.distinct())
}