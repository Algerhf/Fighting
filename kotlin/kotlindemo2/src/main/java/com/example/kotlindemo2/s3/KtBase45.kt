package com.example.kotlindemo2.s3

// TODO 45.Kotlin语言的replace完成加密解密操作
fun main() {
    val sourcePwd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    println("原始密码是：$sourcePwd")

    // 加密操作，就是把字符替换成数字，打乱了，就属于加密了
    var newPwd = sourcePwd.replace(Regex("[AKMNO]")) {
        when (it.value) {  //这里的每一个字符  A  B  C  D
            "A" -> "9"
            "K" -> "3"
            "M" -> "5"
            "N" -> "1"
            "O" -> "4"
            else -> it.value  // 返回字符本身
        }
    }
    println("加密后的密码是：$newPwd")

    // 解密操作
    var sourcePwdNew = newPwd.replace(Regex("[93514]")) {
        when (it.value) {  //这里的每一个字符  A  B  C  D
            "9" -> "A"
            "3" -> "K"
            "5" -> "M"
            "1" -> "N"
            "4" -> "O"
            else -> it.value  // 返回字符本身
        }
    }
    println("解密后的密码是：$sourcePwdNew")
}
