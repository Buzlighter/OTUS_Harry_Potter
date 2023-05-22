package com.otus.characters_screen.di

import com.otus.characters_screen.view.CharactersFragment
import com.otus.core_api.navigation.CharactersMediator
import com.otus.core_api.providers.ExternalProvider
import dagger.Component

@Component(dependencies = [ExternalProvider::class])
interface CharactersComponent {

    companion object {
        fun create(externalProvider: ExternalProvider): CharactersComponent {
            return DaggerCharactersComponent
                .builder()
                .externalProvider(externalProvider)
                .build()
        }
    }

    fun inject(charactersFragment: CharactersFragment)
}