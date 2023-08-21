package com.example.kotlindemo2.s6

interface USB2 {

    // 1、接口var也是不能给接口的成员赋值的（但是有其他办法）
    // 2、任何类 接口 等等 val表示只读的，是不可以在后面动态赋值的 也有其他办法
    val usb_version: String
        get() = (1..100).shuffled().last().toString()

    val usb_device: String
        get() = "高级设备接入USB"

    fun insertUSB(): String
}

class Mouse2 : USB2 {

    override val usb_version: String
        get() = super.usb_version

    override val usb_device: String
        get() = super.usb_device

    override fun insertUSB() = "Mouse $usb_version,$usb_device"

}

// TODO 101.Kotlin语言的接口的默认实现
// 1、注意：这样做是不对的，因为接口成员本来就是用来声明标准的
//         但是可以再接口成员声明时，完成对接口成员的实现
fun main() {
    val p = Mouse2()
    println(p.insertUSB())
}

