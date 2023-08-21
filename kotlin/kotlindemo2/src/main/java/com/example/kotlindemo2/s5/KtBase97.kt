package com.example.kotlindemo2.s5

enum class Exam {
    Fraction1, // 分数差
    Fraction2, // 分数及格
    Fraction3, // 分数良好
    Fraction4, // 分数优秀
}

class Teacher(private val exam: Exam) {
    fun show() =
        when (exam) {
            Exam.Fraction1 -> "该学生分数很差"
            Exam.Fraction2 -> "该学生分数及格"
            Exam.Fraction3 -> "该学生分数良好"
            Exam.Fraction4 -> "该学生分数优秀"
            // else -> 由于我们的show函数，是使用枚举类型来做判断处理的，这个就属于代数数据类型，就不需要写else了
            // 因为when表达式非常明确了，就只有四种类型，不会出现else其他的，所以不需要写
        }

}

// TODO 97.Kotlin语言的代数数据类型
fun main() {
    println(Teacher(Exam.Fraction1).show())
}

