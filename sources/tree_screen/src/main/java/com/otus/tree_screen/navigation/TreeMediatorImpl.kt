package com.otus.tree_screen.navigation

import androidx.fragment.app.FragmentManager
import com.otus.core_api.navigation.TreeMediator
import com.otus.tree_screen.view.TreeFragment
import javax.inject.Inject

class TreeMediatorImpl @Inject constructor(): TreeMediator {

    override fun openTreeScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .replace(containerId, TreeFragment.newInstance())
            .commit()
    }
}