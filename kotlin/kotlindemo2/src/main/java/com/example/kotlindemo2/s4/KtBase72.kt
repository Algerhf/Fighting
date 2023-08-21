package com.example.kotlindemo2.s4

// 主构造函数: 规范来说，都是增加_XXX的方式，临时的输入类型，不能直接用，需要接收下来，成为变量才能用
class KtBase72(_name:String,_sex:Char,_age:Int,_info:String){ // 主构造函数
    var name = _name
        get() = field  // get不允许私有化
        private set(value){
            field = value
        }

    val sex = _sex
        get() = field
        // set(value) {} 只读的，不能修改的，不能set函数定义

    val age = _age
        get() = if(field<0) 0 else field

    val info = _info
        get() = field

    fun show(){
        // println(_name)  临时的输入类型，不能直接使用，需要接收下来，成为变量才能使用
        println(name)
        println(sex)
        println(age)
        println(info)
    }
}

// TODO 72.Kotlin语言的主构造函数学习
fun main() {
    val p = KtBase72(_name="zhang",_info="KT",_age=20,_sex='m')
    println(p.name)
    // p.name = "AAA"  // set函数被私有化了
    p.show()

}