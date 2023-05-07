package com.otus.harry_potter

import android.app.Application
import com.otus.core.CoreProvidersFactory
import com.otus.core_api.providers.AppProvider
import com.otus.core_api.providers.ExternalProvider
import com.otus.core_api.providers.NetworkProvider
import dagger.Component


@Component(
    dependencies = [AppProvider::class, NetworkProvider::class],
    modules = [DispatchersModule::class, MediatorsBinding::class]
)
interface ExternalComponent: ExternalProvider {
    companion object {
        fun create(application: Application): ExternalComponent {
            return DaggerExternalComponent
                .builder()
                .appProvider(AppComponent.create(application))
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .build()
        }
    }
}