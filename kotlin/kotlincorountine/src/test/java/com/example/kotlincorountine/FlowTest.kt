package com.example.kotlincoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

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

    private fun simpleFow() = flow<Int>{
        for (i in 1..3){
            delay(1000)
            emit(i)
        }
    }

    private fun simpleFow2() = flow<Int>{
        println("Flow started")
        for (i in 1..3){
            delay(1000)
            emit(i)
        }
    }

    @Test
    fun `test multi flow`() = runBlocking{

        launch {
            for(k in 1..3){
                println("I'm not blocked：$k")
                delay(1500)
            }
        }
        simpleFow().collect{
            println(it)
        }
    }

    @Test
    fun `test flow cold`() = runBlocking{
        val flow = simpleFow2()
        println("Calling collect...")
        flow.collect{
            println(it)
        }

        println("Calling collect...again")
        flow.collect{
            println(it)
        }

        simpleFow2()
            .flowOn(Dispatchers.IO)
            .buffer(50)
            .conflate()
            .collect{
                println(it)
            }
    }

    private suspend fun performRequest(request:Int):String{
        delay(1000)
        return "respond $request"
    }

    // transform操作符
    @Test
    fun `test transform flow operator`() = runBlocking<Unit> {
        (1..3).asFlow()
            .transform {request->
                emit("Making request $request")
                emit(performRequest(request))
            }.collect{
                println(it)
            }
    }

    // 限长操作符：take
    @Test
    fun `test take flow operator`() = runBlocking<Unit> {
        (1..3).asFlow()
            .take(2)
            .collect{
                println(it)
            }
    }

    // 末端操作符：reduce
    @Test
    fun `test reduce flow operator`() = runBlocking<Unit> {
        (1..5).asFlow()
            .map{it*it}
            .reduce{a,b -> a+b}
            .run(::println)
    }

    // 组合操作符：zip
    @Test
    fun `test zip flow operator`() = runBlocking<Unit> {

        val numbers =(1..5).asFlow()
        val strs = flowOf("one","two","three")
        numbers.zip(strs){a,b->
            "$a -> $b"}
            .collect{
            println(it)
        }
    }

    private fun requestFlow(i:Int) = flow<String> {
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
            .collect{
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
            .collect{
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
            .collect{
                println("$it at ${System.currentTimeMillis() - startTime} ms from startTime")
            }
    }
}