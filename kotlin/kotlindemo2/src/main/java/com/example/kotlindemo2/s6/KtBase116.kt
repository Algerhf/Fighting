package com.example.kotlindemo2.s6

// TODO 116.Kotlin语言的标准函数和泛型扩展函数
fun main() {
    "Derry".mLet {
        it
        true
        "OK"
    }

    123.mLet {
        it
    }

    'C'.mLet {
        it
    }
}

private inline fun <I, O> I.mLet(lambda: (I) -> O) = lambda(this)

