package com.example.kotlindemo2.s6

class ConsumerClass1:Consumer<Animal>{
    override fun consume(item: Animal) {
        println("消费者 Animal")
    }
}

class ConsumerClass2:Consumer<Humanity>{
    override fun consume(item: Humanity) {
        println("消费者 Humanity")
    }
}

class ConsumerClass3:Consumer<Man>{
    override fun consume(item: Man) {
        println("消费者 Man")
    }
}

class ConsumerClass4:Consumer<Woman>{
    override fun consume(item: Woman) {
        println("消费者 Woman")
    }
}

// TODO 110.Kotlin语言的 in逆变
fun main() {

    val p1 : Consumer<Man> = ConsumerClass1()
    val p2 : Consumer<Man> = ConsumerClass2()
    val p3 : Consumer<Man> = ConsumerClass3()
    // val p4 : Consumer<Man> = ConsumerClass4()  // 这里报错


    // 协变：子类泛型声明处  可以接收 父类泛型具体处
}

