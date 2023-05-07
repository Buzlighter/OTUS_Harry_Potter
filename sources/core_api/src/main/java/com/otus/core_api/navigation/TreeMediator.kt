package com.otus.core_api.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface TreeMediator {
    fun openTreeScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}