package com.example.kotlincoroutine.api

import com.example.kotlincoroutine.bean.Users
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val okHttpClient =
    OkHttpClient.Builder().addInterceptor { it.proceed(it.request()).apply { } }.build()

val userServiceApi: UserServiceApi by lazy {
    retrofit2.Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://www.baidu.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .run { create(UserServiceApi::class.java) }
}

interface UserServiceApi {

    @GET("user")
    suspend fun getUser(@Query("name") name: String): Users

}