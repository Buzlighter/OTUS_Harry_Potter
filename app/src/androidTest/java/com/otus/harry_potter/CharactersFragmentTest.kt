package com.otus.harry_potter

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.otus.characters_screen.R.*
import com.otus.characters_screen.view.CharactersAdapter
import com.otus.characters_screen.view.CharactersFragment
import com.otus.main.MainActivity
import com.otus.potions_screen.view.PotionsFragment
import org.junit.Rule
import org.junit.Test

class CharactersFragmentTest {

    @Test
    fun check_last_position_is_Argus_Filch() {
        val fragmentFactory = MainTestFragmentFactory()
        val scenario = launchFragmentInContainer<CharactersFragment>(factory = fragmentFactory)

        //Временное решение, так как idlingResources плохой подход, лучше переписать на kaspresso
        Thread.sleep(2000)

        Espresso.onView(withId(id.characters_recycler))
            .perform(scrollTo<CharactersAdapter.CharacterHolder>(hasDescendant(withText("Argus Filch"))))
    }
}