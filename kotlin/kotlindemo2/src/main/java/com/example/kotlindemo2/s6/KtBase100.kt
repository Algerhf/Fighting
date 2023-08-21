package com.example.kotlindemo2.s6

interface IUSB {
    var usbVersionInfo: String
    var usbInsertDevice: String
    fun insertUSB(): String
}

class Mouse(override var usbVersionInfo: String, override var usbInsertDevice: String) : IUSB {
    override fun insertUSB() = "$usbVersionInfo, $usbInsertDevice"

}

class Keyboard : IUSB {
    override var usbVersionInfo: String = "USB 3.0"
        get() = field
        set(value) {
            field = value
        }

    override var usbInsertDevice: String = "键盘"
        get() = field
        set(value) {
            field = value
        }

    override fun insertUSB() = "$usbVersionInfo, $usbInsertDevice"

}

// TODO 100.Kotlin语言的接口定义
// 1、接口里的成员和接口本身默认都是public open的
// 2、接口不能有主构造，不能有构造
// 3、实现类必须重写接口的成员函数和成员属性
fun main() {
    val p1: IUSB = Mouse("3.0", "鼠标");
    println(p1.insertUSB())

    val p2: IUSB = Keyboard();
    println(p2.insertUSB())
}

