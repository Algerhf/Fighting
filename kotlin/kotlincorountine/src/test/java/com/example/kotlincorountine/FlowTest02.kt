package com.example.kotlincoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.ArithmeticException

// flow异常捕获
class FlowTest02 {

    private fun simpleFlow() = flow {
        for(i in 1..3){
            println("Emitting $i")
            emit(i)
        }
    }

    // 下游使用try-catch
    @Test
    fun `test flow exception`() = runBlocking {
        try {
            simpleFlow().collect{
                println(it)
                check(it<=1){ "Collected $it"}
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

    // 流的完成
    @Test
    fun `test flow exception3`() = runBlocking {
        (1..3).asFlow()
            .collect {
                try {
                    println(it)
                } catch (e: Exception) {
                    //e.printStackTrace()
                }finally {
                    println("Done")
                }
            }
    }

    // 流的完成 onCompletion能检测到上下流的异常，但是不会捕获异常
    // 上流异常用catch    下流异常用try-catch
    @Test
    fun `test flow exception4`() = runBlocking {
        (1..3).asFlow()
            .onCompletion {
                println("caught:$this")
            }.catch {

            }.collect {
                try {
                    println(it)
                } catch (e: Exception) {
                    //e.printStackTrace()
                }finally {
                    println("Done")
                }
            }
    }

}