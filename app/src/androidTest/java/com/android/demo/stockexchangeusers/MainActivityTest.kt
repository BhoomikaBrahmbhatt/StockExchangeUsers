package com.android.demo.stockexchangeusers



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.demo.stockexchangeusers.adapter.MainViewHolder
import com.android.demo.stockexchangeusers.utils.EspressoIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
 class MainActivityTest {
    private val test_item = 0

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun isInView() {
        onView(withId(R.id.progressDialog)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test
    fun `test_isDataLoad_in_adapter`(){

        onView(withId(R.id.recyclerview))
            .perform(scrollToPosition<MainViewHolder>(test_item))

        onView(withId(R.id.recyclerview))
            .check(matches(withViewAtPosition(test_item, hasDescendant(allOf(withId(R.id.id),
                isDisplayed())))))

        onView(withId(R.id.recyclerview))
            .check(matches(withViewAtPosition(test_item, hasDescendant(allOf(withId(R.id.name),
                isDisplayed())))))


        onView(withId(R.id.recyclerview))
            .perform(actionOnItemAtPosition<MainViewHolder>(test_item, click()))

    }


}