package com.otus.core

import com.otus.core_api.providers.NetworkProvider
import com.otus.core_impl.DaggerNetworkComponent
import com.otus.core_impl.HerokuNetworkModule
import com.otus.core_impl.HpNetworkModule

object CoreProvidersFactory {

    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.builder()
            .hpNetworkModule(HpNetworkModule())
            .herokuNetworkModule(HerokuNetworkModule())
            .build()
    }
}