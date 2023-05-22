package com.otus.core_api.providers

import com.otus.core_api.navigation.CharactersMediator
import com.otus.core_api.navigation.PotionsMediator
import com.otus.core_api.navigation.TreeMediator

interface MediatorsProvider {
    fun provideCharactersMediator(): CharactersMediator

    fun provideTreeMediator(): TreeMediator

    fun providePotionsMediator(): PotionsMediator
}