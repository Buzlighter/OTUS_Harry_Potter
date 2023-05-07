package com.otus.core_api.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface DispatcherProvider {
    fun provideDispatcherIO(): CoroutineDispatcher

    fun provideDispatcherMain(): MainCoroutineDispatcher
}