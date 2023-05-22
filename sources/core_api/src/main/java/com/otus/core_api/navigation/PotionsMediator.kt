package com.otus.core_api.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface PotionsMediator {
    fun openPotionsScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}