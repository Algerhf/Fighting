package com.example.kotlincorountine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.ArithmeticException
import java.lang.RuntimeException

class FlowTest03 {

    private fun simpleFlow() = flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }

    // 下游使用try-catch
    @Test
    fun `test flow exception`() = runBlocking {
        try {
            simpleFlow().collect {
                println(it)
                check(it <= 1) { "Collected $it" }
            }
        } catch (e: Exception) {

        }
    }

    // 上游使用响应式编程  catch
    @Test
    fun `test flow exception2`() = runBlocking {
        flow {
            emit(1)
            throw ArithmeticException()
        }.catch {
            println("caught:$this")
            emit(10)  // 可以恢复
        }.flowOn(Dispatchers.IO)
            .collect {
                println(it)
            }
    }

    fun simpleFlow2() = (1..3).asFlow()

    // 流的完成
    @Test
    fun `test flow complete in finally`() = runBlocking {
        try {
            simpleFlow2().collect { println(it) }
        } finally {
            println("Done")
        }
    }

    fun simpleFlow3() = flow<Int>{
        emit(1)
        throw RuntimeException()
    }

    // 流的完成 onCompletion能检测到上下流的异常，但是不会捕获异常
    // 上流异常用catch    下流异常用try-catch
    @Test
    fun `test flow complete in onCompletion`() = runBlocking {
        /*simpleFlow2()
            .onCompletion {
                println("Done")
            }.collect { println(it) }*/

        simpleFlow3()
           .onCompletion {exception ->
               if(exception!=null) println("Flow Completed exception")
           }.catch {exception ->
                println("Caught $exception")
            }
           .collect { println(it) }
    }

}