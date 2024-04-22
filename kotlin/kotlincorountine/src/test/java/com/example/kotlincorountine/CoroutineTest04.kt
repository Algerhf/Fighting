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
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import kotlin.system.measureTimeMillis

class CoroutineTest04 {

    @Test
    fun `test_deal_with_timeout`() = runBlocking<Unit> {
        withTimeout(1300) {
            repeat(1000){i->
                println("job：I‘m sleeping $i ...")
                delay(500L)
            }
        }
    }

    @Test
    fun `test_deal_with_timeout_return null`() = runBlocking<Unit> {
        val result = withTimeoutOrNull(1300) {
            repeat(1000){i->
                println("job：I‘m sleeping $i ...")
                delay(500L)
            }
            "Done"
        }
        println("Result is $result")
    }
}