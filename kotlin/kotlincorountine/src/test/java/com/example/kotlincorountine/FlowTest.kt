package com.example.kotlincorountine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

// Flow-异步流
// 认识：  特性  构建器与上下文  启动  取消与取消检测  缓冲
// 操作符：过度操作符  末端操作符  组合 展平
// 异常：  异常处理   完成

// flow是一种类似于序列的冷流，直到数据被收集时才会执行 流具有连续性

// 流的构建器
// 1、asFlow
// 2、flowOf
// launchIn 可以替换collect 在单独的协程中启动流的收集

// 流的上下文
// 上流默认使用下流的上下文，可以使用flowOn切换上游流的上下文

// 背压
// buffer()         缓存
// conflate()       降低下游的消费能力
// collectLatest()  丢弃中间部分

// 操作符：
// 转换操作符：transform
// 限长操作符：take
// 末端操作符：reduce  fold  toList  toSet  first single
// 组合操作符：zip
// 展平操作符：flatMapConcat flatMapMerge flatMapLatest
class FlowTest {

    // 返回了多个值，但不是异步
    fun simpleList(): List<Int> = listOf(1, 2, 3)

    // 返回了多个值，是同步
    fun simpleSequence() = sequence {
        for (i in 1..3) {
            Thread.sleep(1000) // 阻塞，假装在计算
            yield(i)
        }
    }

    // 返回了多个值，是异步，一次性返回了多个值
    suspend fun simpleList2(): List<Int> {
        delay(1000)
        return listOf(1, 2, 3)
    }

    // 返回了多个值，是异步
    suspend fun simpleFow() = flow<Int> {
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    @Test
    fun `test multiple values`() {
        // simpleList().forEach { value -> println(value) }

        // simpleSequence().forEach { value -> println(value) }
    }

    @Test
    fun `test multiple values2`() = runBlocking<Unit> {
        simpleList2().forEach { value -> println(value) }
    }

    @Test
    fun `test multiple values3`() = runBlocking<Unit> {
        launch {
            for (k in 1..3) {
                println("I'm not blocked：$k")
                delay(1500)
            }
        }
        simpleFow().collect { value -> println(value) }
    }

    fun simpleFow2() = flow<Int> {
        println("Flow started")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    @Test
    fun `test flow is cold`() = runBlocking {
        val flow = simpleFow2()
        println("Calling collect...")
        flow.collect {
            println(it)
        }

        println("Calling collect...again")
        flow.collect {
            println(it)
        }
    }

    @Test
    fun `test flow continuation`() = runBlocking<Unit> {
        (1..5).asFlow().filter {
            it % 2 == 0
        }.map {
            "string $it"
        }.collect {
            println("collect $it")
        }
    }

    @Test
    fun `test flow builder`() = runBlocking<Unit> {
//        flowOf("one", "two", "three")
//            .onEach { delay(1000) }
//            .collect {
//                println("collect $it")
//            }

        (1..3).asFlow().collect(::println)
    }

    fun simpleFow3() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    fun simpleFow4() = flow {
        withContext(Dispatchers.IO) {
            println("Flow started ${Thread.currentThread().name}")
            for (i in 1..3) {
                delay(1000)
                emit(i)
            }
        }
    }

    @Test
    fun `test flow context`() = runBlocking<Unit> {
        simpleFow3().collect { value ->
            println("Collected $value ${Thread.currentThread().name}")
        }
    }

    fun simpleFow5() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    @Test
    fun `test flow on`() = runBlocking<Unit> {
        simpleFow5().collect { value ->
            println("Collected $value ${Thread.currentThread().name}")
        }
    }

    fun events() = (1..3)
        .asFlow()
        .onEach { delay(100) }
        .flowOn(Dispatchers.Default)

    @Test
    fun `test flow launch`() = runBlocking<Unit> {
        events().onEach { event -> println("event $event ${Thread.currentThread().name}") }
            .launchIn(this)
            .join()
    }

    fun simpleFow6() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
            println("Emitting $i")
        }
    }

    @Test
    fun `test cancel flow`() = runBlocking<Unit> {
        withTimeoutOrNull(2500) {
            simpleFow6().collect(::println)
        }
        println("Done")
    }

    fun simpleFow7() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..5) {
            emit(i)
            println("Emitting $i")
        }
    }

    @Test
    fun `test cancel flow check`() = runBlocking<Unit> {
        /*simpleFow7().collect { value ->
            println(value)
            if (value == 3) {
                cancel()
            }
        }*/
        (1..5).asFlow().cancellable().collect { value ->
            println(value)
            if (value == 3) cancel()
        }
    }

    fun simpleFow8() = flow<Int> {
        for (i in 1..3) {
            delay(100)
            emit(i)
            println("Emitting $i   ${Thread.currentThread().name}")
        }
    }

    @Test
    fun `test flow back pressure`() = runBlocking<Unit> {
        val time = measureTimeMillis {
            simpleFow8()
                // .flowOn(Dispatchers.Default)
                //.buffer(50)
                .conflate()
                .collect { value ->
                    delay(300)
                    println("Collected $value ${Thread.currentThread().name}")
                }
        }
        println("Collected in $time ms")
    }
}