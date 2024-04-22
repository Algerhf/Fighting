package com.example.kotlincorountine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import kotlin.system.measureTimeMillis

class CoroutineTest02 {

    @Test
    fun `test_scope_cancel`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            delay(1000)
            println("job 1")
        }

        scope.launch {
            delay(1000)
            println("job 2")
        }

        delay(100)
        scope.cancel()
        delay(2000)
    }

    @Test
    fun `test_brother_cancel`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(1000)
            println("job 1")
        }

        val job2 = scope.launch {
            delay(1000)
            println("job 2")
        }

        delay(100)
        job1.cancel()
        delay(2000)
    }

    @Test
    fun `test_cancellation_exception`() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            try {
                delay(1000)
                println("job1")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        delay(100)
        job1.cancel(CancellationException("取消"))
        job1.join()
    }

    @Test
    fun `test_cancel_cpu_task_by_isActive`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5 && isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++} ...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main：I'm tired of waiting")
        job.cancelAndJoin()
        println("main：Now I can quit")
    }

    @Test
    fun `test_cancel_cpu_task_by_ensureActive`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                ensureActive()
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++} ...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main：I'm tired of waiting")
        job.cancelAndJoin()
        println("main：Now I can quit")
    }

    @Test
    fun `test_cancel_cpu_task_by_yield`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                ensureActive()
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++} ...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main：I'm tired of waiting")
        job.cancelAndJoin()
        println("main：Now I can quit")
    }
}