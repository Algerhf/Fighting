package com.example.kotlindemo2.s5

// object 实现单例模式
object KtBase87 {
    /*
        object对象类背后做了什么事情

        public static final KtBase87 INSTANCE;

        private KtBase87(){}

        public final void show(){
            System.out.println();
        }

        static{
            KtBase87 var = new KtBase87();
            INSTANCE = var;
            System.out.println("KtBase87 init...");
        }
     */

    init {
        println("KtBase87 init...")
    }

    fun show() {
        println("我是show函数")
    }
}

// TODO 87.Kotlin语言的对象声明学习
fun main() {
    // object KtBase87 既是单例的实例，也是类名
    println(KtBase87)  // 背后：KtBase87.INSTANCE
    println(KtBase87)
    println(KtBase87)

    // 背后代码:KtBase87.INSTANCE.show()
    println(KtBase87.show())
}