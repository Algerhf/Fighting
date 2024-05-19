package com.example.kotlincorountine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import kotlin.system.measureTimeMillis

class CoroutineTest01 {

    @Test
    fun `test_coroutine_Builder`() = runBlocking {
        val job1 = launch {
            delay(200)
            println("job1 finished")
        }

        val job2 = async {
            delay(200)
            println("job2 finished")
            "Job2 result"
        }

        println(job2.await())
    }

    @Test
    fun `test_coroutine_join`() = runBlocking {
        val job1 = launch {
            delay(2000)
            println("one")
        }
        job1.join()

        val job2 = launch {
            delay(200)
            println("two")
        }

        val job3 = launch {
            delay(200)
            println("three")
        }
    }

    @Test
    fun `test_coroutine_await`() = runBlocking {
        val job1 = async {
            delay(2000)
            println("one")
        }
        job1.await()

        val job2 = async {
            delay(200)
            println("two")
        }

        val job3 = async {
            delay(200)
            println("three")
        }

    }

    @Test
    fun `test_sync`() = runBlocking {
        val time = measureTimeMillis {
            val one = doOne()
            val two = doTwo()
            println("result:${one + two}")
        }

        println("cost time $time ms")
    }

    @Test
    fun `test_combine_async`() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doOne() }
            val two = async { doTwo() }
            println("result:${one.await() + two.await()}")

            // 这种方式跟test_coroutine_sync方法差不多，错误用法
//            val one = async { doOne() }.await()
//            val two = async { doTwo() }.await()
//            println("result:${one + two}")
        }

        println("cost time $time ms")
    }

    private suspend fun doOne(): Int {
        delay(1000)
        return 16
    }

    private suspend fun doTwo(): Int {
        delay(1000)
        return 14
    }

    @Test
    fun `test_start_mode`() = runBlocking {
/*        val job = launch(start = CoroutineStart.ATOMIC) {
            delay(10000)
            println("job finished")
        }
        delay(1000)
        job.cancel()*/

/*        val job2 = async(start = CoroutineStart.LAZY) {
            29
        }
        // ... 计算
        job2.cancel()
        job2.await()*/

        val job4 = async(context = Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
            println("thread：${Thread.currentThread().name}")
        }
    }

    @Test
    fun `test_coroutine_scope_builder`() = runBlocking {
        coroutineScope {
            val job1 = launch {
                delay(400)
                println("jop1 finished.")
            }

            val job2 = async {
                delay(200)
                println("jop2 finished.")
                "job2 result"
                throw IllegalArgumentException()
            }
        }
    }

    @Test
    fun `test_supervisor_scope_builder`() = runBlocking {
        supervisorScope {
            val job1 = launch {
                delay(400)
                println("jop1 finished.")
            }

            val job2 = async {
                delay(200)
                println("jop2 finished.")
                "job2 result"
                throw IllegalArgumentException()
            }
        }
    }
}