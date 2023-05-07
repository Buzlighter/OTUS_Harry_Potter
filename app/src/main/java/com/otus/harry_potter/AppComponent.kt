package com.otus.harry_potter

import android.app.Application
import android.content.Context
import com.otus.core_api.providers.AppProvider
import dagger.BindsInstance
import dagger.Component


@Component
interface AppComponent: AppProvider {

    companion object {

        private var appComponent: AppProvider? = null
        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also {
                    appComponent = it
                }
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }

}