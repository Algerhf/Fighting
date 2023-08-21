package com.example.kotlindemo2.s3

// TODO 38.Kotlin语言中的非空断言操作符特点
fun main(){

    var name:String? = "zhangsan"
    name = null

    // name.capitalize()  // name是可空类型，可能是null,想要使用name，必须给出补救措施

    // 补救措施  我们已经学习了 ?
    var r = name!!.capitalize()  // !! 断言  不管name是不是null都执行，这个和java一样了
    println(r)

    // 结论：如果百分百能保证name是有值的，那么才能使用断言 !! 否则有java空指针异常的风险
}