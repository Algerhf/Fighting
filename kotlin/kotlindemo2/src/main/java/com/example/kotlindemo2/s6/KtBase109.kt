package com.example.kotlindemo2.s6

// 生产者 out T  协变
interface Producer<out T> {

    // 只能被读取
    fun produce(): T
}

// 消费者 in T  逆变
interface Consumer<in T> {

    // 只能被消费
    fun consume(item: T)
}

// 生产者&消费者   T  不变
interface ProduceAndConsume<T> {
    // 被读取
    fun produce(): T

    // 被消费
    fun consume(item: T)
}

class ProduceClass1 : Producer<Animal> {
    override fun produce(): Animal {
        println("生产者 Animal")
        return Animal()
    }
}

class ProduceClass2 : Producer<Humanity> {
    override fun produce(): Humanity {
        println("生产者 Humanity")
        return Humanity()
    }
}

class ProduceClass3 : Producer<Man> {
    override fun produce(): Man {
        println("生产者 Man")
        return Man()
    }
}

class ProduceClass4 : Producer<Woman> {
    override fun produce(): Woman {
        println("生产者 Woman")
        return Woman()
    }
}

open class Animal

open class Humanity : Animal()

open class Man : Humanity()

open class Woman : Humanity()

// TODO 109.Kotlin语言的 out协变
fun main() {

    val p: Producer<Animal> = ProduceClass1()
    val p1: Producer<Animal> = ProduceClass1()
    val p2: Producer<Animal> = ProduceClass1()
    val p3: Producer<Animal> = ProduceClass1()

    // out:泛型的子类对象 可以赋值给泛型的父类对象
}

