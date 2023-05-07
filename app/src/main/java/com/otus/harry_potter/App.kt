package com.otus.harry_potter

import android.app.Application
import com.otus.core_api.providers.ExternalAppHub
import com.otus.core_api.providers.ExternalProvider

class App: Application(), ExternalAppHub {

    companion object {
        private var externalComponent: ExternalComponent? = null
    }

    override fun getExternalProvider(): ExternalProvider {
        return externalComponent ?: ExternalComponent.create(this).also {
            externalComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        getExternalProvider()
    }
}