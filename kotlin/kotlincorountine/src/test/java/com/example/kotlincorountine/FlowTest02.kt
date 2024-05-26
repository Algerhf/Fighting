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
}