package com.otus.harry_potter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.otus.characters_screen.view.CharactersFragment
import com.otus.potions_screen.view.PotionsFragment
import com.otus.tree_screen.view.TreeFragment

class MainTestFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            CharactersFragment::class.java.name -> {
                CharactersFragment()
            }
            TreeFragment::class.java.name -> {
                TreeFragment()
            }
            PotionsFragment::class.java.name -> {
                PotionsFragment()
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
}