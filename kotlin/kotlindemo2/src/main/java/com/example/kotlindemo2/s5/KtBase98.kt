package com.example.kotlindemo2.s5

// 密封类，我们成员，就必须有类型，并且继承本类
sealed class Exams {
    object Fraction1 : Exams()   // 分数差
    object Fraction2 : Exams()   // 分数及格
    object Fraction3 : Exams()   // 分数良好
    class Fraction4(val studentName: String) : Exams()   // 分数优秀

}

class Teachers(private val exam: Exams) {
    fun show() =
        when (exam) {
            is Exams.Fraction1 -> "该学生分数很差"
            is Exams.Fraction2 -> "该学生分数及格"
            is Exams.Fraction3 -> "该学生分数良好"
            is Exams.Fraction4 -> "该学生分数优秀:该学生的姓名是：${this.exam.studentName}"
            // else -> 由于我们的show函数，是使用枚举类型来做判断处理的，这个就属于代数数据类型，就不需要写else了
            // 因为when表达式非常明确了，就只有四种类型，不会出现else其他的，所以不需要写
        }

}

// TODO 98.Kotlin语言的密封类
/**
 * 2、密封类
 *   用来表示受限的类继承结构：当一个值为有限几种的类型, 而不能有任何其他类型时。
 *   在某种意义上，他们是枚举类的扩展：枚举类型的值集合 也是受限的，但每个枚举常量只存在一个实例，
 *   而密封类 的一个子类可以有可包含状态的多个实例。
 *   声明一个密封类，使用 sealed [siːld]  修饰类，密封类可以有子类，但是所有的子类都必须要内嵌在密封类中。
 */
fun main() {
    println(Teachers(Exams.Fraction1).show())
}

