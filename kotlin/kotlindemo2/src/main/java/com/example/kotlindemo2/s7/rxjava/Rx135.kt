package com.example.kotlindemo2.s7.rxjava

// 手写RxJava,全部用KT的基础来写
fun main() {
    // create 输入源：没有任何参数给你  输出源：输出就行（所有类型）
    create {
        3.14f
    }.map{
        "你的值是：$this"
    }.map {
        "[$this]"
    }.map {
        "@@$this@@"
    }.observe {
        println(this)
    }
}

// 中转站，保存我们的记录
class RxJavaCore<T>(val valueItem:T) // 主构造，接收你传递进来的信息，此消息就是create最后一行的返回

inline fun <I,O> RxJavaCore<I>.map(mapAction:I.() -> O) = RxJavaCore(valueItem.mapAction())

inline fun <T> create(action:() -> T) = RxJavaCore(action())

inline fun <T> RxJavaCore<T>.observe(observerAction:T.() -> Unit) = observerAction(valueItem)

