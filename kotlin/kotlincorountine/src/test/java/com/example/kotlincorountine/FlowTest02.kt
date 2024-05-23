package com.example.kotlincorountine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.ArithmeticException

class FlowTest02 {

    private suspend fun performRequest(request: Int): String {
        delay(1000)
        return "respond $request"
    }

    // transform操作符
    @Test
    fun `test transform flow operator`() = runBlocking<Unit> {
        /*(1..3).asFlow()
            .map { request ->
                performRequest(request)
            }.collect {
                println(it)
            }*/

        (1..3).asFlow()
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }.collect {
                println(it)
            }
    }

    fun numbers() = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }

    // 限长操作符：take
    @Test
    fun `test limit length operator`() = runBlocking<Unit> {
        numbers().take(2).collect { println(it) }
    }

    // 末端操作符：reduce
    @Test
    fun `test terminal operator`() = runBlocking<Unit> {
        (1..5).asFlow()
            .map { it * it }
            .reduce { a, b -> a + b }
            .run(::println)
    }

    // 组合操作符：zip
    @Test
    fun `test zip`() = runBlocking<Unit> {
        val numbs = (1..4).asFlow()
        val strs = flowOf("one", "two", "three")
        numbs.zip(strs) { a, b ->
            "$a -> $b"
        }.collect {
            println(it)
        }
    }

    @Test
    fun `test zip2`() = runBlocking<Unit> {
        val numbs = (1..4).asFlow().onEach { delay(300) }
        val strs = flowOf("one", "two", "three").onEach { delay(400) }
        val startTime = System.currentTimeMillis()
        numbs.zip(strs) { a, b ->
            "$a -> $b"
        }.collect {
            println(it)
            println("$it at ${System.currentTimeMillis() -startTime} ms from start")
        }
    }

    private fun requestFlow(i: Int) = flow<String> {
        emit("$i:First")
        delay(500)
        emit("$i:Second")
    }

    // 展平操作符：flatMapConcat
    @Test
    fun `test flatMapConcat flow operator`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow()
            .onEach { delay(100) }
            .flatMapConcat { requestFlow(it) }  // 先拆分1..3  依次与requestFlow结合
            .collect {
                println("$it at ${System.currentTimeMillis() - startTime} ms from startTime")
            }
    }

    // 展平操作符：flatMapMerge
    @Test
    fun `test flatMapMerge flow operator`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow()
            .onEach { delay(100) }
            .flatMapMerge { requestFlow(it) } // 先拆分requestFlow  依次与1..3结合
            .collect {
                println("$it at ${System.currentTimeMillis() - startTime} ms from startTime")
            }
    }

    // 展平操作符：flatMapLatest
    @Test
    fun `test flatMapLatest flow operator`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow()
            .onEach { delay(100) }
            .flatMapLatest { requestFlow(it) } // 中间的不要了
            .collect {
                println("$it at ${System.currentTimeMillis() - startTime} ms from startTime")
            }
    }

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

    // 流的完成
    @Test
    fun `test flow exception3`() = runBlocking {
        (1..3).asFlow()
            .collect {
                try {
                    println(it)
                } catch (e: Exception) {
                    //e.printStackTrace()
                } finally {
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
                } finally {
                    println("Done")
                }
            }
    }

}