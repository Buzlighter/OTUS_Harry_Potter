package com.otus.core_api.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface CharactersMediator {
    fun openCharactersScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}