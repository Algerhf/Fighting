package com.example.kotlincorountine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.junit.Test
import java.io.IOException
import java.lang.AssertionError

class CoroutineTest05 {

    @Test
    fun `test_corountine_context`() = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun `test_corountine_context_extend`() = runBlocking<Unit> {
        val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
        val job = scope.launch {
            println("${coroutineContext[Job]} ${Thread.currentThread().name}")
            val result = async {
                println("${coroutineContext[Job]} ${Thread.currentThread().name}")
                "OK"
            }.await()
        }
        job.join()
    }

    @Test
    fun `test_corountine_context_extend2`() = runBlocking<Unit> {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler get $exception")
        }
        val scope = CoroutineScope(Job() + Dispatchers.Main + coroutineExceptionHandler)
        val job = scope.launch(Dispatchers.IO) {

        }
        job.join()
    }

    @Test
    fun `test_exception_propagation`() = runBlocking<Unit> {
        val job = GlobalScope.launch {
            try {
                throw IndexOutOfBoundsException()
            } catch (e: Exception) {
                println("Catch IndexOutOfBoundsException")
            }
        }
        job.join()

        val deferred = GlobalScope.async {
            try {
                throw ArithmeticException()
            } catch (e: Exception) {
                println("Catch ArithmeticException1")
            }
        }

        try {
            deferred.await()
        } catch (e: Exception) {
            println("Catch ArithmeticException2")
        }
    }

    @Test
    fun `test exception propagation2`() = runBlocking{
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            async {
                throw IllegalArgumentException()
            }
        }
        job.join()
    }

    @Test
    fun `test_supervisor_job`() = runBlocking<Unit> {
        val supervisor = CoroutineScope(SupervisorJob())
        val job1 = supervisor.launch {
            delay(100)
            println("child 1")
            throw IllegalArgumentException()
        }
        val job2 = supervisor.launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("child 2 finished")
            }
        }
        joinAll(job1, job2)
    }

    @Test
    fun `test_supervisor_scope`() = runBlocking<Unit> {
        supervisorScope {
            launch {
                delay(100)
                println("child 1")
                throw IllegalArgumentException()
            }
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("child 2 finished")
            }
        }
    }

    @Test
    fun `test_supervisor_scope2`() = runBlocking<Unit> {
        try {
            supervisorScope {
                val child = launch {
                    try {
                        println("The child is sleeping")
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("The child is cancelled")
                    }
                }
                yield()
                println("Throwing an exception from the scope")
                throw AssertionError()
            }
        } catch (e: AssertionError) {
            println("catch an assertion error")
        }
    }

    @Test
    fun `test_coroutine_exception_handler`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Catch $exception")
        }

        val job = GlobalScope.launch(handler) {
            throw AssertionError()
        }
        val deferred = GlobalScope.async(handler) {
            throw ArithmeticException()
        }
        job.join()
        deferred.await()
    }

    @Test
    fun `test_coroutine_exception_handler2`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Catch $exception")
        }

        val scope = CoroutineScope(Job())
        val job = scope.launch(handler) {
            launch {
                throw IllegalArgumentException()
            }
        }
        job.join()
    }

    @Test
    fun `test_coroutine_exception_handler3`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Catch $exception")
        }

        // 这种写法的异常不会被捕获
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            launch(handler) {
                throw IllegalArgumentException()
            }
        }
        job.join()
    }

    @Test
    fun `test_cancel_and_exception`() = runBlocking<Unit> {
        val job = launch {
            val child = launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    println("child is canceled")
                }
            }
            yield()
            println("Canceling child")
            child.cancelAndJoin()
            yield()
            println("Parent is not canceled")
        }
        job.join()
    }

    @Test
    fun `test_cancel_and_exception2`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch $throwable")
        }
        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    withContext(NonCancellable) {
                        println("Children are cancelled,but exception is not handled util all children terminate")
                        delay(100)
                        println("The first child finished its non cancellable block")
                    }
                }
            }
            launch {
                delay(10)
                println("Second child throws an exception")
                throw ArithmeticException()
            }
        }
        job.join()
    }

    @Test
    fun `test_exception_aggregation`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch $throwable ${throwable.suppressed.contentToString()}")
        }
        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw ArithmeticException()
                }
            }
            launch {
                delay(100)
                throw IOException()
            }
        }
        job.join()
    }

}