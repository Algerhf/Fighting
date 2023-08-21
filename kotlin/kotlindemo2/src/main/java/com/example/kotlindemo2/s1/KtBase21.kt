package com.example.kotlindemo2.s1

// TODO 21.kotlin语言的反引号中函数名的特点
fun main() {

    // 第一种情况
    `登录功能_2021年8月8日_测试环境下测试登录功能需求编码人是Derry`("derry","123456")

    // 第二种情况:跟java互调
    // in 和 is 在java里是方法名 但是在kt里就是关键字，怎么办？使用反引号
    KtBase21.`in`()
    KtBase21.`is`()

    // 第三种情况：很少发生
    `65488888888`("zs","123456")
}

private fun `登录功能_2021年8月8日_测试环境下测试登录功能需求编码人是Derry`(name:String,pwd:String){
    println("name is $name pwd is $pwd")
}

private fun `65488888888`(name:String,pwd:String){
    // 写了很复杂的功能，核心功能
    println("name is $name pwd is $pwd")
}

// 公司私有文档   `65488888888` ===  函数说明