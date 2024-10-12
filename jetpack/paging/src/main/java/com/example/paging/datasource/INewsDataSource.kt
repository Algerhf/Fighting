package com.example.paging.datasource

import com.example.paging.News

interface INewsDataSource {
    fun getUser(): News
}