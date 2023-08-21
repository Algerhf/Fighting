package com.example.kotlindemo2.s1

// TODO 16.kotlin语言的函数头学习
fun main() {
    method01(99, "jack")
    method01(88, "ws")
}

// 函数默认都是public修饰
// 其实Kotlin的函数更规范，先有输入，再有输出
private fun method01(age: Int, name: String): Int {
    println("你的姓名是：$name 你的年龄是$age")
    return 200
}

/* 上面的Kt函数，最后会变成下面的java代码：
    private static final int method01(int age,String name){
        String var2 = "你的姓名是："+ name + ",你的年龄是："+ age;
        System.out.println(var2);
        return 200;
    }
 */