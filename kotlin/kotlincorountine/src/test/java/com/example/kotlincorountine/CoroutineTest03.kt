package com.example.kotlincorountine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
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
import kotlinx.coroutines.withContext
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class CoroutineTest03 {

    @Test
    fun `test_release_resources`() = runBlocking<Unit> {
        val job = launch {
            try {
                repeat(1000){i->
                    println("job：I‘m sleeping $i ...")
                    delay(500L)
                }
            }finally {
                println("job：I’m running finally")
            }
        }
        delay(1300)
        println("main：I'm tried of waiting")
        job.cancelAndJoin()
        println("main：Now I can quit.")
    }

    @Test
    fun `test_use_function`() = runBlocking<Unit> {
        BufferedReader(FileReader("f://study.txt")).use {
            val lines: List<String> = it.readLines()
            lines.forEach {line ->
                println(line)
            }
        }
    }

    @Test
    fun `test_cancel_with_NonCancellable`() = runBlocking<Unit> {
        val job = launch {
            try {
                repeat(1000){i->
                    println("job：I‘m sleeping $i ...")
                    delay(500L)
                }
            }finally {
                withContext(NonCancellable){
                    println("job：I’m running finally")
                    delay(100)
                    println("job：And I've just delayed for 1 sec because I’m non-cancellable")
                }
            }
        }
        delay(1300)
        println("main：I'm tried of waiting")
        job.cancelAndJoin()
        println("main：Now I can quit.")
    }
}