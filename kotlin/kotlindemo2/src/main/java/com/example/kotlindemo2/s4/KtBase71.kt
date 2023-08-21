package com.example.kotlindemo2.s4

class KtBase71{
    val name = "Derry"

    /*
      背后的隐式代码

      @NotNull
      private String name = "Derry";

      public String getName(){
        return name;
      }

     */

    // 计算属性，下面这样写 get函数覆盖了field 内容本身，相当于field失效了，无用了
    val number2:Int
        get() = (1..1000).shuffled().first() // 从1到1000之间取出随机数，返回给getNumber2()函数

    /*
      背后的隐式代码

      为什么没看到number2的属性定义
      答：因为属于计算属性的功能，根本在getNumber2函数里面，就没有用到number2属性，所以number2属性失效了，无用了，以后用不到了
      public int getNumber2(){
        return (1..1000).shuffled().first();
      }
    */

    var info:String? = ""
    // 防范竞态条件  当你调用成员，这个成员可能为null 可能为空值，就必须采用 防范竞态条件  这个是KT编程的规范化
    fun getShowInfo():String{

        // 这种写法，就属于防范竞态条件，我们可以看到专业的KT开发者，有大量这种代码
        return info?.let { if(it.isBlank()) "info你是空值" else "最终info结果是：$it" }?:"info你原来为null,请检查代码"
    }
}

// TODO 71.Kotlin语言的 计算属性与防范竞态条件
fun main() {

    // 隐式调用get方法
    println(KtBase71().name)

    // 编译不通过   val 根本没有set函数
    // KtBase71().name = "jack"

    println(KtBase71().number2)
    println(KtBase71().number2)

}