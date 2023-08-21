package com.example.kotlindemo

/**
 * 1、数据类
 * JVM想要实现无参构造，必须给主构造函数的属性指定默认初始化值
 *
 * 1、主构造函数至少有一个参数
 * 2、主构造函数里的属性必须使用var或val标识
 * 3、数据类不能是  抽象abstract 继承open  密封sealed  内部inner
 * 4、数据类不能继承其他类，但可以实现接口
 *
 */
data class DataClass(val name: String, val age: Int) {

}

/**
 * 2、密封类
 *   用来表示受限的类继承结构：当一个值为有限几种的类型, 而不能有任何其他类型时。在某种意义上，他们是枚举类的扩展：枚举类型的值集合 也是受限的，但每个枚举常量只存在一个实例，
 *   而密封类 的一个子类可以有可包含状态的多个实例。
 *   声明一个密封类，使用 sealed [siːld]  修饰类，密封类可以有子类，但是所有的子类都必须要内嵌在密封类中。
 */
sealed class Expr {
    data class Const(val number: Double) : Expr()
    data class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

fun eval(expr: Expr): Double = when (expr) {
    is Expr.Const -> expr.number
    is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
    Expr.NotANumber -> Double.NaN
}




/**
 * ------------------------------------------------------------
 */

fun main(args: Array<String>) {
    val jack = DataClass("jack", 20)
    val olderJack = jack.copy(age = 30)
    println(jack)
    println(olderJack)
    println("-----------------------------------")

    // 组件函数用于数据类的解析声明
    // 数据类解构声明
    val (name, age) = jack
    println("name = $name,age = $age")

    // 标准数据类
//    val pair = Pair("hello", 10)
//    val (first, second) = pair
//    println("first = $first,second = $second")

    val triple = Triple("hello", "world", "kotlin")
    val (first, second, third) = triple
    println("first = $first , third = $third")

    println("-----------------------------------")

    val sum = Expr.Sum(Expr.Const(1.0), Expr.Const(2.0))
    val eval = eval(sum)
    println("eval = $eval")

}
