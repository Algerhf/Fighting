package com.example.kotlincoroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import org.junit.Test


// select 多路复用-返回最快的那个
class ChannelTest {

    @Test
    fun `test know channel`() = runBlocking<Unit> {
        val channel = Channel<Int>()

        val produce = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }

        val consumer = GlobalScope.launch {
            while (true) {

                var element = channel.receive()
                println("receive $element")
            }
        }

        joinAll(produce, consumer)
    }

    @Test
    fun `test know channel2`() = runBlocking<Unit> {
        val channel = Channel<Int>()

        val produce = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }

        val consumer = GlobalScope.launch {
            while (true) {
                delay(2000)
                var element = channel.receive()
                println("receive $element")
            }
        }

        joinAll(produce, consumer)
    }

    @Test
    fun `test iterate channel`() = runBlocking<Unit> {
        val channel = Channel<Int>(Channel.UNLIMITED)

        val produce = GlobalScope.launch {

            for (x in 1..5) {
                channel.send(x * x)
                println("send ${x * x}")
            }
        }

        val consumer = GlobalScope.launch {
            val iterator = channel.iterator()
            while (iterator.hasNext()) {

                val element = iterator.next()
                println("receive $element")
                delay(2000)
            }
        }

        joinAll(produce, consumer)
    }

    @Test
    fun `test fast producer channel`() = runBlocking<Unit> {

        val receiveChannel = GlobalScope.produce<Int> {
            repeat(100) {
                delay(1000)
                send(it)
            }
        }

        val consumer = GlobalScope.launch {
            for (i in receiveChannel) {
                println("receive $i")
            }
        }

        consumer.join()
    }

    @Test
    fun `test fast consumer channel`() = runBlocking<Unit> {

        val sendChannel = GlobalScope.actor<Int> {
            while (true) {
                val element = receive()
                println(element)
            }
        }

        val producer = GlobalScope.launch {
            for (i in 0..3) {
                sendChannel.send(i)
            }
        }

        producer.join()
    }

    @Test
    fun `test close channel`() = runBlocking<Unit> {

        val channel = Channel<Int>()

        // 生产者
        val produce = GlobalScope.launch {
            List(3) {
                channel.send(it)
                println("send $it")
            }

            channel.close()
            println(
                """close channel
                | - ClosedForSend:${channel.isClosedForSend}
                | - isClosedForReceive:${channel.isClosedForReceive}""".trimMargin()
            )
        }

        // 消费者
        val consumer = GlobalScope.launch {
            for (element in channel) {
                println("receive $channel")
                delay(3000)
            }

            println(
                """after Consuming
                | - ClosedForSend:${channel.isClosedForSend}
                | - isClosedForReceive:${channel.isClosedForReceive}""".trimMargin()
            )
        }

        joinAll(produce, consumer)
    }

    //
    @Test
    fun `test broadcast channel`() = runBlocking<Unit> {

        val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)

        // 生产者
        val produce = GlobalScope.launch {
            List(3) {
                delay(100)
                broadcastChannel.send(it)
            }

            broadcastChannel.close()
        }

        // 消费者
        List(3) {index->
            GlobalScope.launch {
                val receiveChannel = broadcastChannel.openSubscription()
                for (element in receiveChannel) {
                    println("[#${index}] receive $element")
                }
            }
        }.joinAll()
    }
}