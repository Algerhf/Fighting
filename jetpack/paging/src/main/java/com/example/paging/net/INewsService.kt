package com.example.paging.net

import com.example.paging.News
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsService {

    @GET
    suspend fun getUser(@Query("page") page: Int): News
}