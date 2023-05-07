package com.otus.main.di

import com.otus.core_api.providers.ExternalProvider
import com.otus.main.MainActivity
import dagger.Component


@Component(dependencies = [ExternalProvider::class])
interface MainComponent {

    companion object {

        fun create(externalProvider: ExternalProvider): MainComponent {
            return DaggerMainComponent
                .builder()
                .externalProvider(externalProvider)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
}