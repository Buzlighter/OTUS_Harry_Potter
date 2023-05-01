package com.otus.core_impl

import com.otus.core_api.remote.Heroku
import com.otus.core_api.remote.HerokuApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_HEROKU_URL = "https://wizard-world-api.herokuapp.com"

@Module
class HerokuNetworkModule {
    @Singleton
    @Provides
    @Heroku
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    @Heroku
    fun provideRetrofit(@Heroku okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BASE_HEROKU_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideHerokuApi(@Heroku retrofit: Retrofit): HerokuApi {
        return retrofit.create(HerokuApi::class.java)
    }
}