package com.example.kotlindemo2.s4

class KtBase79 {

    val database1 = readSqlServerDatabaseAction()  // 直接加载，懒汉式

    val database2 by lazy {
        readSqlServerDatabaseAction() // 使用时自动加载
    }

    private fun readSqlServerDatabaseAction() {
        println("加载中...")
        println("加载中...")
        println("加载中...")
        println("加载中...")
        println("加载中...")
        println("加载中...")
        println("加载中...")
        println("加载完成...")
    }

}

// TODO 79.Kotlin语言的惰性初始化by lazy学习
// lateinit是在使用的时候，手动加载的懒加载方式，然后再使用
// 惰性初始化by lazy   是在使用的时候，自动加载的懒加载方式，然后再使用
fun main() {

}