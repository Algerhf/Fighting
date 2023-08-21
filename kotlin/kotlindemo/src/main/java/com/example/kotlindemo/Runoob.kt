package com.example.kotlindemo

import java.util.*

class Runoob (var firstName: String = "zhangsan") {

    fun setInterface(testInterface: TestInterface){
        testInterface.test()
    }
}