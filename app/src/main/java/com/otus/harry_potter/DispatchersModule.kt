package com.otus.harry_potter

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Singleton

@Module
class DispatchersModule {

    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideDispatcherMain(): MainCoroutineDispatcher = Dispatchers.Main
}