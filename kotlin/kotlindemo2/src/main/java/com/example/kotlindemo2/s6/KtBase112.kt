package com.example.kotlindemo2.s6

data class ObjectClass1(val name: String, val age: Int, val study: String)
data class ObjectClass2(val name: String, val age: Int, val study: String)
data class ObjectClass3(val name: String, val age: Int, val study: String)

class KtBase112 {

    // 所有的功能，写在函数上
    // 默认随时输出一个对象，如果此对象和用户指定的对象不一致，我们就启用备用对象，否则就直接返回对象
    inline fun <reified T> randomOrDefault(defaultLambdaAction: () -> T): T? {
        val objList = listOf(
            ObjectClass1("王二", 18, "学习C"),
            ObjectClass2("张三", 19, "学习C++"),
            ObjectClass3("李四", 20, "学习KT"),
        )

        val randomObj = objList.shuffled().first()

        return randomObj.takeIf { it is T } as T? ?: defaultLambdaAction()
    }

}

// TODO 112.Kotlin语言的reified关键字
fun main() {
    KtBase112().randomOrDefault { ObjectClass1("幸运儿", 19, "学习C++") }.run(::println)
}

