package com.otus.harry_potter

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.otus.characters_screen.view.CharactersAdapter
import com.otus.characters_screen.view.CharactersFragment
import com.otus.main.MainActivity
import com.otus.main.R
import com.otus.tree_screen.R.*
import com.otus.tree_screen.view.NamesAdapter
import com.otus.tree_screen.view.TreeAdapter
import com.otus.tree_screen.view.TreeFragment
import org.junit.Rule
import org.junit.Test

class TreeFragmentTest {

    @Test
    fun check_weasley_in_right_position() {
        val fragmentFactory = MainTestFragmentFactory()
        val scenario = launchFragmentInContainer<TreeFragment>(factory = fragmentFactory)

        //Временное решение, так как idlingResources плохой подход, лучше переписать на kaspresso
        Thread.sleep(3000)

        Espresso.onView(withId(id.names_recycler))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<NamesAdapter.NameHolder>(2, click())
            )

        Espresso.onView(withId(id.tree_recycler))
            .check(matches(hasDescendant(withText("Ron Weasley"))))
    }
}