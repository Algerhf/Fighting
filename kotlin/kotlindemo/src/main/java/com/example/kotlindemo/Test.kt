package com.example.kotlindemo

class Test {

    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    fun foo() {
        val ints = intArrayOf(5, 4, 0, 2, 1)
//        ints.forEach lit@{
//            if(it==0)return@lit
//            println(it)
//        }

        ints.forEach {
            if (it == 0) return@forEach
            println(it)
        }
    }
}

/**
 * 导入包
 * 默认引入
 * 函数的定义 fun
 * 可变长参数函数 vararg
 * 匿名函数 val sumLambda:(Int,Int)->Int={x,y -> x+y}
 * 常量与变量  var  val
 * 注释
 * 字符串模板 $
 * NULL检查机制 ?  !!  ?  ?:
 * 类型推断与类型自动转换  is  !is
 * 区间  in  until downTo  step        in 1 until 10的范围是[1,10)
 *
 * 基本数据类型：Byte Short Int Long Float Double  字符不属于数值类型，是一个独立的数据类型。
 * 字面常量  L f/F  0x  0b  _可以分割数字，使数字更易读
 * 比较两个数字  ==表示比较两个值的大小  ===比较对象地址
 * 类型转换  to...系列函数
 * 位操作符   shl  shr  ushr  and  or  xor  inv
 * 字符 ''     转义字符：  '\r'  '\n' '\t'  '\b' '\\' '\'' '\"'  '\$'
 * 布尔 Boolean   &&  ||  !
 * 字符串 """  trimMargin
 *
 * if
 * when
 *
 * for
 * while
 * do...while
 *
 * 类的定义 class
 * 类的属性 getter setter
 * 主构造器
 * 次构造器
 * 抽象类
 * 嵌套类
 * 内部类
 * 匿名内部类
 * 类的修饰符  类属性修饰符：abstract final enum open annotation
 *            访问权限修饰符：private protected public internal
 */
fun main(args: Array<String>) {

    val runoob = Runoob()
    println(runoob.firstName)

    runoob.setInterface(object :TestInterface{
        override fun test() {
           println("对象表达式创建匿名内部类")
        }
    })

}