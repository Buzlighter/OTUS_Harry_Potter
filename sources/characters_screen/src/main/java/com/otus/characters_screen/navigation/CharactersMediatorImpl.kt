package com.otus.characters_screen.navigation

import androidx.fragment.app.FragmentManager
import com.otus.characters_screen.view.CharactersFragment
import com.otus.core_api.navigation.CharactersMediator
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

class CharactersMediatorImpl @Inject constructor(): CharactersMediator {

    override fun openCharactersScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .replace(containerId, CharactersFragment.newInstance())
            .commit()
    }
}