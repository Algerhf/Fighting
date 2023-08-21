package com.example.kotlindemo2.s6

open class MyAnyClass(var name: String) {  // 祖宗类  顶级父类

}

open class PersonClass(name: String) : MyAnyClass(name) { // 子类

}

open class StudentClass(name: String) : PersonClass(name) { // 子类

}

open class TeacherClass(name: String) : PersonClass(name) { // 子类

}

class DogClass(name: String) {  // 其他类 另类

}

// T:PersonClass  相当于 Java的  T  extends PersonClass
class KtBase106<T : PersonClass>(val inputTypeValue: T, private val isR: Boolean = true) {
    // 万能对象返回器
    fun getObj() = inputTypeValue.takeIf { isR }
}

// TODO 106.Kotlin语言的泛型类型约束
fun main() {
    val any = MyAnyClass("Derry1")
    val per = PersonClass("Derry1")
    val stu = StudentClass("Derry1")
    val tea = TeacherClass("Derry1")
    val dog = DogClass("Derry1")

    /*val r1 = KtBase106(any).getObj()
    println(r1)*/

    val r2 = KtBase106(per).getObj()
    println(r2)

    val r3 = KtBase106(stu).getObj()
    println(r3)

    val r4 = KtBase106(tea).getObj()
    println(r4)

    /*val r5 = KtBase106(dog).getObj()
    println(r5)*/
}

