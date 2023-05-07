package com.otus.potions_screen.navigation

import androidx.fragment.app.FragmentManager
import com.otus.core_api.navigation.PotionsMediator
import com.otus.potions_screen.view.PotionsFragment
import javax.inject.Inject

class PotionsMediatorImpl @Inject constructor(): PotionsMediator {

    override fun openPotionsScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .replace(containerId, PotionsFragment.newInstance())
            .commit()
    }
}