package com.otus.core_api.providers

import android.content.Context

interface AppProvider {
    fun provideAppContext(): Context
}