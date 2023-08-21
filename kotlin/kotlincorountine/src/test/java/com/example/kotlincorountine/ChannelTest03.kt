package com.example.kotlincoroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

// 协程的并发安全问题
class ChannelTest03 {

    @Test
    fun `test not safe concurrent`() = runBlocking {
        var count = 0
        List(1000) {
            GlobalScope.launch {
                count++
            }
        }.joinAll()
        println(count) // 打印小于1000
    }

    @Test
    fun `test safe concurrent`() = runBlocking {
        var count = AtomicInteger(0)
        List(1000) {
            GlobalScope.launch {
                count.incrementAndGet()
            }
        }.joinAll()
        println(count.get()) // 打印=1000
    }

    @Test
    fun `test safe concurrent tools`() = runBlocking {
        var count = 0
        val mutex = Mutex()
        List(1000) {
            GlobalScope.launch {
                mutex.withLock { count++ }
            }
        }.joinAll()
        println(count) // 打印=1000
    }

    @Test
    fun `test safe concurrent tools2`() = runBlocking {
        var count = 0
        val semaphore = Semaphore(1)
        List(1000) {
            GlobalScope.launch {
                semaphore.withPermit { count++ }
            }
        }.joinAll()
        println(count) // 打印=1000
    }

    // 避免使用外部变量
    @Test
    fun `test avoid outer variable`() = runBlocking {
        var count = 0
        val result = count + List(1000) {
            GlobalScope.async { 1 }
        }.map { it.await() }.sum()
        println(result) // 打印=1000
    }
}