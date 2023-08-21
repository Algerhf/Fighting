package com.example.kotlindemo

class TestLambda{
    fun higherOrderFunction(onClick: (Int) -> Unit) {
        onClick.invoke(20)
    }
}

/**
 * 当lambda
 */
fun main(args: Array<String>) {

    val testLambda = TestLambda()

    testLambda.higherOrderFunction {
        println("lambda表达式")
        println(it)
    }

    // 匿名函数类型作为参数
    testLambda.higherOrderFunction(fun(a: Int): Unit {
        println("lambda表达式")
    })

    // 简化写法一
    testLambda.higherOrderFunction({ a: Int ->
        println("lambda表达式")
    })

    // 如果lambda是方法的最后一个参数，可以把大括号写在括号外面
    testLambda.higherOrderFunction() { a ->
        println("lambda表达式")
    }

    // 如果lambda是方法的唯一一个参数，可以把方法后面的括号省略
    testLambda.higherOrderFunction { a ->
        println("lambda表达式")
    }

    // 如果lambda只有一个参数，而且不用它，那么这个参数可以不写
    testLambda.higherOrderFunction {
        println("lambda表达式")
    }

    // 如果lambda只有一个参数，就算要用它，这个参数也可以省略不写，用it表示省略的那个参数
    testLambda.higherOrderFunction {
        println("lambda表达式")
    }

    val b = { a: Int ->
        println("lambda表达式")
    }

    testLambda.higherOrderFunction(b)
}