package com.example.kotlindemo2.s5

enum class WEEK {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

// TODO 95.Kotlin语言的枚举类学习
fun main() {
    println(WEEK.MONDAY)
    println(WEEK.TUESDAY)

    // 枚举的值也是枚举本身
    println(WEEK.WEDNESDAY is WEEK)
}

