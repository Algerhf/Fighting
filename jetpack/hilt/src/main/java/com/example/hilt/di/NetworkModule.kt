package com.example.hilt.di

import com.example.hilt.ApiService
import com.example.hilt.AuthInterceptor
import com.example.hilt.AuthInterceptorOkHttpClient
import com.example.hilt.OtherInterceptor
import com.example.hilt.OtherInterceptorOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @AuthInterceptorOkHttpClient
    @Provides
    fun providerAuthOkhttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @OtherInterceptorOkHttpClient
    @Provides
    fun providerOtherOkhttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(otherInterceptor)
            .build()
    }

    @Provides
    fun providerApiService(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.baidu.com")
            .build()
            .create(ApiService::class.java)
    }
}