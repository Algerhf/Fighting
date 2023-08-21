package com.example.kotlincoroutine

import kotlinx.coroutines.*
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException
import kotlin.system.measureTimeMillis

class CoroutineTest01 {

    @Test
    fun `test_coroutine_Builder`() = runBlocking {
        val job1 = launch {
            delay(2000)
            println("job1 finished")
        }

        val job2 = async {
            delay(2000)
            println("job2 finished")
            "job2 result"
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
    fun `test_coroutine_sync`() = runBlocking {
        val time = measureTimeMillis {
            val one = doOne()
            val two = doTwo()
            println("result:${one + two}")
        }

        println("cost time $time ms")
    }

    @Test
    fun `test_coroutine_async`() = runBlocking {
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

    fun `test_user`() = runBlocking<Unit>{
        BufferedReader(FileReader("")).use {

        }
    }

    @Test
    fun `test exception propagation2`() = runBlocking{
        val job1 = GlobalScope.launch {
            try {
                throw IndexOutOfBoundsException()
            } catch (e: Exception) {
                println("IndexOutOfBoundsException")
            }
        }
        job1.join()

        val deffered = GlobalScope.async {
            throw ArithmeticException()
        }
        deffered.join()
        delay(1000)

       supervisorScope {
           launch {

           }
       }

        coroutineScope {

        }
    }
}