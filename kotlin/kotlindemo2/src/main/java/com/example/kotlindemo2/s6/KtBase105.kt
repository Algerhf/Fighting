package com.example.kotlindemo2.s6

class KtBase105<T>(val isMap: Boolean = false, val inputType: T) {

    // 模仿RxJava  T是要变化的输入类型 R是变换后的输出类型
    // 要去map返回的类型是R? == 有可能是R  有可能是null
    inline fun <R> map(mapAction: (T) -> R) = mapAction(inputType).takeIf { isMap }
}

inline fun <I, O> map(input: I, isMap: Boolean = true, mapActionLambda: (I) -> O) =
    if (isMap) mapActionLambda(input) else null

// TODO 105.Kotlin语言的泛型变换实战
// 1.类 isMap map takeIf map是什么类型
// 2.map int -> str  最终接收是什么类型
// 3.map per -> stu  最终接收是什么类型
// 4.验证是否是此类型 与 null
fun main() {

    // 2.map int -> str  最终接收是什么类型
    val p = KtBase105(isMap = true, inputType = 5434)
    val r = p.map {
        it
        it.toString()  // lambda最后一行是返回值
        "我的it是：$it"  // lambda最后一行是返回值
    }

    // 4.验证是否是此类型 与 null
    println(r is String)
    // println(r is String?)
    println(r)
    println(r ?: "大哥你是null,你在搞什么飞机。。。。，你是不是传入了isMap为false")

    // 3.map per -> stu 最终接收是什么类型
    val p2 = KtBase105(true, Persons("Jack", 25))
    val r2 = p2.map {
        Students(it.name, it.age)
    }
    println(r2)

    // map函数：模仿RxJava的变换操作
    val r3 = map(123) {
        it.toString()
        "map包裹[$it]" // lambda最后一行是返回值
    }
    println(r3)
}


data class Persons(var name: String, var age: Int)
data class Students(var name: String, var age: Int)

