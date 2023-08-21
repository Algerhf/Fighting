package com.example.kotlindemo2.s3

// TODO 36.在Kotlin中使用带let安全调用
fun main(){

    var name:String?=null
    name = "Derry"
    name = ""

    // name是可空类型，如果真的是null，?后面的这段代码不执行，就不会引发空指针异常
    val r = name?.let {
        // it == name本身
        // 如果能够执行到这里面的，it一定不为null
        if(it.isBlank()) "Default" else "[$it]"
    }

    println(r)
}