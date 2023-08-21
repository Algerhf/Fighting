package com.example.kotlindemo

class D {


    fun bar(){
        println("d... bar")
    }
}

class E{
    fun bar(){
        println("E...bar")
    }

    fun D.foo(){
        bar()
        this@E.bar()
    }

    fun caller(d:D){
        d.foo()
    }
}

fun main(args:Array<String>){
    val e = E()
    e.caller(D())
}