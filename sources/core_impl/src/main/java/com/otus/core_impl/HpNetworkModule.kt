package com.otus.core_impl

import com.otus.core_api.remote.Heroku
import com.otus.core_api.remote.Hp
import com.otus.core_api.remote.HpApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

private const val BASE_HP_URL = "https://hp-api.onrender.com"

@Module
class HpNetworkModule {

    @Singleton
    @Provides
    @Hp
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    @Singleton
    @Provides
    @Hp
    fun provideRetrofit(@Hp okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BASE_HP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideHpApi(@Hp retrofit: Retrofit): HpApi {
        return retrofit.create(HpApi::class.java)
    }



}

