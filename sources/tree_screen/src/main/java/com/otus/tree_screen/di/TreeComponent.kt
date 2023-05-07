package com.otus.tree_screen.di

import com.otus.core_api.providers.ExternalProvider
import com.otus.tree_screen.view.TreeFragment
import dagger.Component

@Component(dependencies = [ExternalProvider::class])
interface TreeComponent {

    companion object {
        fun create(externalProvider: ExternalProvider): TreeComponent {
            return DaggerTreeComponent
                .builder()
                .externalProvider(externalProvider)
                .build()
        }
    }

    fun inject(treeFragment: TreeFragment)
}