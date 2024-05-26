package com.example.kotlincorountine

import com.example.kotlincoroutine.bean.Users
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.selects.select
import org.junit.Test
import java.io.File

data class Respond<T>(val user: T, val isLocal: Boolean)

// 模拟从本地获取数据
fun CoroutineScope.getUserFromLocal() = async(Dispatchers.IO) {
    delay(300)
    val file = File("data.txt")
    val exist =  file.exists()
    if(!exist){
        println("文件不存在 ${file.absolutePath}")
        return@async null
    }
    File("data.txt").apply {
        //println(absoluteFile)
    }.readText().let {
        Moshi.Builder().build().adapter(Users::class.java).fromJson(it)
    }
}

// 模拟从网络获取数据
fun CoroutineScope.getUserFromNetwork() = async(Dispatchers.IO) {
    delay(200)
    Users("lisi", "aaaaaaaaa")
}

// Flow多路复用
class ChannelTest02 {
    @Test
    fun `test select await`() = runBlocking<Unit> {

        GlobalScope.launch {
            val localRequest = getUserFromLocal()
            val remoteRequest = getUserFromNetwork()
            val userResponse = select<Respond<Users?>> {
                localRequest.onAwait { Respond(it, true) }
                remoteRequest.onAwait { Respond(it, false) }
            }
            userResponse.user?.let { println(it) }
        }.join()
    }

    @Test
    fun `test select channel`() = runBlocking<Unit> {
        val channels = listOf(Channel<Int>(), Channel<Int>())
        GlobalScope.launch {
            delay(100)
            channels[0].send(200)
        }

        GlobalScope.launch {
            delay(50)
            channels[0].send(100)
        }

        val result = select<Int?> {
            channels.forEach { channel ->
                channel.onReceive { it }
            }
        }
        println(result)

        delay(1000)
    }

    @Test
    fun `test selectClause`() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            delay(100)
            println("job 1")
        }

        val job2 = GlobalScope.launch {
            delay(10)
            println("job 2")
        }
        select<Unit> {
            job1.onJoin{ println("job 1 onJoin") }
            job2.onJoin{ println("job 2 onJoin") }
        }
        delay(1000)
    }

    @Test
    fun `test selectClause2`() = runBlocking<Unit> {
        val channels = listOf(Channel<Int>(), Channel<Int>())
        println(channels)
        launch(Dispatchers.IO) {
            select<Unit> {
                launch {
                    delay(10)
                    channels[1].onSend(200){
                        println("sent on $it")
                    }
                }

                launch {
                    delay(100)
                    channels[0].onSend(100){
                        println("sent on $it")
                    }
                }
            }
        }

        GlobalScope.launch {
            println(channels[0].receive())
        }

        GlobalScope.launch {
            println(channels[1].receive())
        }

        delay(1000)
    }

    @Test
    fun `test select flow`() = runBlocking<Unit> {
        coroutineScope {
            listOf(::getUserFromLocal, ::getUserFromNetwork)
                .map {
                    it.call()
                }.map {
                    flow {
                        emit(it.await())
                    }.onCompletion {
                        println("...")
                    }.catch { println(it.suppressed.contentToString()) }
                }.merge().collect(::println)
        }
    }
}