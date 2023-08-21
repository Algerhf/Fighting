package com.example.kotlindemo2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var str:String = resources.getString(R.string.name,"666")

        val mediaPlayer = MediaPlayer()

        val continuation = suspend {
            5
        }.createCoroutine(object:Continuation<Int> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {

            }
        })

        continuation.resume(Unit)

    }
}