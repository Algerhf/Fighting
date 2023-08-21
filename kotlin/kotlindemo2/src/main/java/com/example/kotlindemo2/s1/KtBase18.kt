package com.example.kotlindemo2.s1

// TODO 17.kotlin语言的具名函数参数
fun main() {

    // 可以不用按照顺序传递参数
    login(age = 18, password = "123", username = "Derry", phoneNumber = "123456")
    login(age = 19, password = "456", username = "Jack", phoneNumber = "54631")
}

private fun login(username: String, password: String, phoneNumber: String, age: Int) {
    println("username：$username,password: $password,phoneNumber:$phoneNumber,age:$age")
}
