package com.otus.harry_potter

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.otus.potions_screen.R.*
import com.otus.potions_screen.view.PotionsFragment
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Test

class PotionsFragmentTest {

    @Test
    fun check_if_ingredients_on_table_after_click_on_btn_next() {
        val fragmentFactory = MainTestFragmentFactory()
        val scenario = launchFragmentInContainer<PotionsFragment>(factory = fragmentFactory)

        //Временное решение, так как idlingResources плохой подход, лучше переписать на kaspresso
        Thread.sleep(2000)

        Espresso.onView(withId(id.next_btn))
            .perform(click())

        Thread.sleep(2000)

        Espresso.onView(withId(id.cooking_list_layout))
            .check(matches(hasMinimumChildCount(1)))

    }
}