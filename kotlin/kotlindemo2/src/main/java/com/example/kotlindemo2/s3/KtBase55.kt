package com.example.kotlindemo2.s3

// TODO 55.Kotlin语言的 takeIf内置函数
fun main(){

    // name.takeIf{ true/false}
    // true：直接返回name
    // false：直接返回null

    println(checkPermissionAction("Root", "123456"))

    // 小结：一般大部分情况下，都是takeIf + 空合并操作符  一起使用
}

public fun checkPermissionAction(name:String,pwd:String):String{
    return name.takeIf { permissionSystem(name,pwd) }?:"您没有权限"
}

private fun permissionSystem(name:String,pwd:String) = name=="Root"&&pwd=="123456"


