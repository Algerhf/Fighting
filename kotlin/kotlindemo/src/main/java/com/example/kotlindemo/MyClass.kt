package com.example.kotlindemo

class MyClass {
    companion object{

    }
}

fun MyClass.Companion.foo(){
    println("伴生对象的扩展函数")
}

val MyClass.Companion.no:Int
    get() = 10

fun main(args:Array<String>){
//    println("no:${MyClass.no}")
//    MyClass.foo()

    val x = MyClass.Companion
}