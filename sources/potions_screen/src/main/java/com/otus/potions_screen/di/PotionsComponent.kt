package com.otus.potions_screen.di

import com.otus.core_api.providers.ExternalProvider
import com.otus.potions_screen.view.PotionsFragment
import dagger.Component

@Component(dependencies = [ExternalProvider::class])
interface PotionsComponent {
    companion object {
        fun create(externalProvider: ExternalProvider): PotionsComponent {
            return DaggerPotionsComponent
                .builder()
                .externalProvider(externalProvider)
                .build()
        }
    }

    fun inject(potionsFragment: PotionsFragment)
}