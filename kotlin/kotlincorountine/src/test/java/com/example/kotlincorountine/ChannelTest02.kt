package com.example.kotlincoroutine

import com.example.kotlincoroutine.bean.User
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion
import org.junit.Test
import java.io.File

// 模拟从本地获取数据
fun CoroutineScope.getUserFromLocal() = async(Dispatchers.IO) {
    delay(100)
    File("data.txt").apply{
        //println(absoluteFile)
    }.readText().let {
        Moshi.Builder().build().adapter(User::class.java).fromJson(it)
    }
}

// 模拟从网络获取数据
fun CoroutineScope.getUserFromNetwork() = async(Dispatchers.IO) {
    User("lisi", "aaaaaaaaa")
}

@Test
fun getUser() = runBlocking<Unit> {
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

// Flow多路复用
class ChannelTest02 {
    @Test
    fun getUserByChannel() = runBlocking<Unit> {

        GlobalScope.launch {
            var job1 = getUserFromLocal()
            var job2 = getUserFromLocal()
//            select<User> {
//                job1.onAwait{}
//                job2.onAwait{}
//            }
        }
    }


}