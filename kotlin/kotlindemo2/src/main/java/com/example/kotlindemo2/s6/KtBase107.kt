package com.example.kotlindemo2.s6

class KtBase107<T>(vararg objects: T, var isMap: Boolean = true) {

    var objectArray: Array<out T> = objects

    fun showObj(index: Int): T? = objectArray[index].takeIf { isMap } ?: null

    fun <O> mapObj(index: Int, mapAction: (T?) -> O) = mapAction(objectArray.getOrNull(index))
}

// TODO 107.Kotlin语言的可变参数vararg关键字（动态参数）
fun main() {
    val p = KtBase107("Derry", false, 53545, 3.14f, 3.15, null, 'C', isMap = true)

    println(p.showObj(0))
    println(p.showObj(1))
    println(p.showObj(2))
    println(p.showObj(3))
    println(p.showObj(4))
    println(p.showObj(5))
    println(p.showObj(6))

    println()

    // it的类型，实际上真正的类型 {Comparable<*>? & java.io.Serializable}
    var r2 = p.mapObj(0) {
        if (it is String) {
            it.length
        }
    }
    println(r2)

    val p2: KtBase107<String> = KtBase107("aaa", "bbb", "ccc", "ddd", "eee", isMap = true)
    p2.mapObj(2) {
        "我要把你变成String:$it"
    }

}

