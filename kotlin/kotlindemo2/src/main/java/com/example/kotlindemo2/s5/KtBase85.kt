package com.example.kotlindemo2.s5

// kt所有的类默认是final修饰的，不能被继承，和java相反
// kt所有的函数默认是final修饰的，不能被继承，和java相反
open class Person3(val name: String) {
    private fun showName() = "父类的名字是：name $name"

    open fun myPrintln() = println(showName())

    fun methodPerson() = println("我是父类的方法...")
}

class Student3(val subName: String) : Person3(subName) {

    override fun myPrintln() = println("子类的名字是：subName $subName")

    fun methodStudent() = println("我是子类的方法...")
}

// TODO 85.Kotlin语言的智能类型转换学习
fun main() {
    val p: Person3 = Student3("李四")

    (p as Student3).methodStudent()

    p.methodStudent()
    p.methodStudent()

    // 智能类型转换：会根据上面as转换成的类型，自动明白现在的类型就是上面的类型
}