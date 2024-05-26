package com.example.kotlincoroutine.download

import java.io.File

sealed class DownloadStatus {
    data object None : DownloadStatus()
    data class Progress(val progress: Int) : DownloadStatus()
    data class Done(val file: File) : DownloadStatus()
    data class Error(val throwable: Throwable) : DownloadStatus()
}