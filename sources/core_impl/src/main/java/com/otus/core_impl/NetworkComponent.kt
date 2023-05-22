package com.otus.core_impl

import com.otus.core_api.providers.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HpNetworkModule::class, HerokuNetworkModule::class])
interface NetworkComponent: NetworkProvider