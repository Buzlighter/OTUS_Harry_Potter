package com.otus.core_api.providers

import com.otus.core_api.remote.HpApi

interface NetworkProvider {
    fun provideHpApi(): HpApi
}