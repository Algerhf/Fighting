package com.example.kotlindemo2.s4

class KtBase70 {
    var name = "Derry"

    /*
      @NotNull
      private String name = "Derry";

      public void setName(@NotNull String name){
        this.name = name;
      }

      public String getName(){
        return name;
      }
     */
    var value = "ABCDEFG"
        // 下面的隐式代码，不写也有，就是下面整个样子
        get() = field
        set(value) {
            field = value
        }

    var info = "abcd"
        get() = field.capitalize()
}

// TODO 70.Kotlin语言的定义类与 field关键字学习
fun main() {

    // 隐式调用set方法
    KtBase70().name = "jack"

    // 隐式调用get方法
    println(KtBase70().name)

}