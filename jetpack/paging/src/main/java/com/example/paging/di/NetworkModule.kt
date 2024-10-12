package com.example.paging.di

import com.example.paging.net.INewsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(okHttpClient: OkHttpClient): INewsService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(INewsService::class.java)
    }
}