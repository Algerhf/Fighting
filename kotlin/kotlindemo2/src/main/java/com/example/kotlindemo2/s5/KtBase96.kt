package com.example.kotlindemo2.s5

class LimbsInfo(var limbsInfo: String, var length: Int) {
    fun show() {
        println("$limbsInfo 的长度是：$length")
    }
}

enum class Limbs(private var limbsInfo: LimbsInfo) {
    LEFT_HAND(LimbsInfo("左手", 88)),
    RIGHT_HAND(LimbsInfo("右手", 88)),
    LEFT_FOOT(LimbsInfo("左脚", 100)),
    RIGHT_FOOT(LimbsInfo("右脚", 100));

    // 1、这个时候再定义单调的枚举值，就会报错了，必须所有枚举值保持一致的效果
    // 2、枚举的主构造的参数必须和枚举（的参数）保持一致

    fun show() = "四肢是：${limbsInfo.limbsInfo},长度是${limbsInfo.length}"

    fun updateData(info: LimbsInfo) {
        limbsInfo.limbsInfo = info.limbsInfo
        limbsInfo.length = info.length
    }
}

// TODO 96.Kotlin语言的枚举类定义函数
fun main() {
    // 显示枚举值
    println(Limbs.LEFT_HAND.show())
    println(Limbs.RIGHT_HAND.show())
    println(Limbs.LEFT_FOOT.show())
    println(Limbs.RIGHT_FOOT.show())

    // 更新枚举值
    Limbs.LEFT_HAND.updateData(LimbsInfo("左手", 99))
    Limbs.RIGHT_HAND.updateData(LimbsInfo("右手", 99))
    Limbs.LEFT_HAND.updateData(LimbsInfo("左脚", 120))
    Limbs.LEFT_HAND.updateData(LimbsInfo("右脚", 120))
}

