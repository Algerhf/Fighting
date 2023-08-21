package com.example.kotlindemo2.s6

// in T    out T   声明处指定关系，声明处泛型  这个是java没有的功能

// 整个SetClass里面所有的成员 泛型相关  只能修改，不能读取
class SetClass<in T>() {

    fun set1(item: T) {
        println("set1 你要设置的item是 $item")
    }

    fun set2(item: T) {
        println("set2 你要设置的item是 $item")
    }

    fun set3(item: T) {
        println("set3 你要设置的item是 $item")
    }

    // 编译不通过
/*    fun get1():T{
        return null
    }*/
}

// 整个GetClass里面所有的成员 泛型相关  只能读取，不能修改
class GetClass<out T>() {

    fun get1(): T? {
        return null
    }

    fun get2(): T? {
        return null
    }

    fun get3(): T? {
        return null
    }

    // 编译不通过
/*    fun set1(item:T){

    }*/
}

// TODO 111.Kotlin语言的使用 in 和 out
fun main() {
    val p1 = SetClass<String>()
    p1.set1("aaa")
    p1.set1("bbb")

    val p2 = GetClass<String>()
    p2.get1()
    p2.get2()
}

