package com.example.kotlincoroutine.download

import android.util.Log
import com.example.kotlincoroutine.utils.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.lang.IllegalStateException

object DownloadManager {
    fun download(url: String, file: File): Flow<DownloadStatus> {
        return flow {
            val request = Request.Builder().url(url).get().build()
            val response = OkHttpClient.Builder().build().newCall(request).execute()
            if (response.isSuccessful) {
                response.body()!!.let { body ->
                    val total = body.contentLength()
                    Log.d("DownloadManager", "total = $total")
                    file.outputStream().use { output ->
                        val input = body.byteStream()
                        var emittedProgress = 0L
                        input.copyTo(output) { byteCopied ->
                            val progress = byteCopied * 100 / total
                            if (progress - emittedProgress > 5) {
                                Log.d("DownloadManager", "progress = $progress")
                                emittedProgress = progress
                                delay(100)
                                emit(DownloadStatus.Progress(progress.toInt()))
                            }
                        }
                    }
                    emit(DownloadStatus.Done(file))
                }
            } else {
                emit(DownloadStatus.Error(IllegalStateException("请求错误")))
            }
        }.catch {
            file.delete()
            emit(DownloadStatus.Error(it))
        }.flowOn(Dispatchers.IO)
    }
}