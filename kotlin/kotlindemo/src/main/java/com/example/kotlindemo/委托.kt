package com.example.kotlindemo

import com.example.demo.R
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 1、类委托：      by b
 * 2、属性委托：    by Delegate()
 * 3、延迟属性：    by lazy{}
 * 4、可观察属性：  by Delegates.observable(t:T){prop,old,new -> ...}
 * 5、映射委托：    by map
 * 6、延迟初始化:   by Delegates.notNull()
 * 7、局部委托属性  by lazy(()->Local)
 * 8、提供委托
 */





/**
 * 1、类委托
 */
// 创建接口
interface Base {
    fun print()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b

/**
 * 2、属性委托
 */
class Example {
    var p: String by Delegate()
}

/**
 * 定义一个被委托的类
 */
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef,这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为$value")
    }
}

/**
 * 3、标准委托：延迟属性委托
 */
val lazyValue: String by lazy{
    println("computed")
    "Hello"
}

/**
 * 4、可观察属性委托 observable
 */
class Users {
    var name: String by Delegates.observable("初始值") { prop, old, new ->
        println("旧值：$old -> 新值：$new")
    }
}

/**
 * 5、把属性储存在映射中
 */
class Site(map: MutableMap<String, Any?>) {
    var name: String by map
    var url: String by map
}

/**
 * 6、notNull属性委托：适用于那些无法在初始化阶段就确定属性值的场合。
 */
class Foos {
    var noNullVar: String by Delegates.notNull()
}

/**
 * 7、局部委托属性
 */

class Local {
    fun isValid(): Boolean {
        return true
    }

    fun doSomething() {
        println("doSomething")
    }
}

fun localExample(a: Int, local: () -> Local) {

    val memorizedLocal by lazy(local)

    if (a > 10 && memorizedLocal.isValid()) {
        memorizedLocal.doSomething()
    }
}

/**
 * 提供委托
 */
class ResourceLoader<T>(id: T) {
    operator fun provideDelegate(thisRef: MyUI, prop: KProperty<*>):Delegate{
        checkProperty(thisRef, prop.name)
        // 创建委托
        return Delegate()
    }

    private fun checkProperty(thisRef: MyUI, name: String) {

    }
}

fun <T> bindResource(id:T): ResourceLoader<T> {
    return ResourceLoader(id)
}

class MyUI {
    val image by bindResource(R.mipmap.ic_launcher)
    val text by bindResource(R.string.app_name)
}


fun main() {
    // 1、类委托
    val b = BaseImpl(10)
    Derived(b).print() // 输出 10

    // 2、属性委托
    val e = Example()
    println(e.p)
    e.p = "Runoob"
    println(e.p)
    println("----------------------------------------------")

    // 3、标准委托：
    println(lazyValue)
    println(lazyValue)
    println("----------------------------------------------")

    // 4、可观察属性
    val users = Users()
    users.name = "第一次赋值"
    users.name = "第二次赋值"
    println("----------------------------------------------")

    // 5、把属性储存在映射中
    val map: MutableMap<String, Any?> = mutableMapOf(
        "name" to "菜鸟教程",
        "url" to "www.runoob.com"
    )
    val site = Site(map)
    println(site.name)
    println(site.url)

    map["name"] = "Google"
    map["url"] = "www.google.com"
    println(site.name)
    println(site.url)
    println("-----------------------------------------------------------")

    // 6、NOT NULL
    val foos = Foos()
    foos.noNullVar = "hello kotlin"
    println(foos.noNullVar)
    println("-----------------------------------------------------------")

    // 7、局部委托属性
    localExample(30){
        Local()
    }
    println("-----------------------------------------------------------")
}









