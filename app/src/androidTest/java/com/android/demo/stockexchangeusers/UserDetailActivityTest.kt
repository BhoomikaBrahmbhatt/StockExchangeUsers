package com.android.demo.stockexchangeusers

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import android.os.Bundle

import androidx.test.core.app.ApplicationProvider

import android.content.Intent
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.android.demo.stockexchangeusers.adapter.MainViewHolder
import com.android.demo.stockexchangeusers.repository.*
import com.android.demo.stockexchangeusers.utils.EspressoIdlingResource
import com.google.gson.Gson
import org.hamcrest.core.AllOf


@RunWith(JUnit4::class)
class UserDetailActivityTest {
     val TAG_ID = 818544


    private val itemUser  = Items(
        BadgeCounts(8,1,0),35,12345678,TAG_ID,"London",
        "https://www.gravatar.com/avatar/894891fa86576454f1cab28c28625425?s=256&d=identicon&r=PG",
        "Test User")
    var intent: Intent? = Intent(ApplicationProvider.getApplicationContext(), UserDetailActivity::class.java)
        .putExtra(AllApi.USER_DATA, Gson().toJson(itemUser))

    private val tags = Tags(1,0,1,0,"java")
    lateinit var userResponse : UsersResponse
    lateinit var tagResponse : TagsResponse
    var userList = ArrayList<Items>()
    var tagList = ArrayList<Tags>()
    private val test_item = 0


    @get:Rule
    val activityRule = ActivityScenarioRule<UserDetailActivity>(intent)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        userList.add(itemUser)
        tagList.add(tags)

        userResponse = UsersResponse(userList , true,300,299 )
        tagResponse = TagsResponse(tagList)

    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun isInView() {
        Espresso.onView(ViewMatchers.withId(R.id.progressDialog))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.imageview))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_location))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_location_value))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_reputaion))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_reputaion_value))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_creation_date))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_badges))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_gold_badge))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_silver_badge))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_bronze_badge))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.textlabeltags))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

    }

    @Test
    fun `test_isDataLoad_in_adapter`(){

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .perform(RecyclerViewActions.scrollToPosition<MainViewHolder>(test_item))

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        test_item, ViewMatchers.hasDescendant(
                            AllOf.allOf(
                                ViewMatchers.withId(R.id.tag_name),
                                ViewMatchers.isDisplayed()
                            )
                        )
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        test_item, ViewMatchers.hasDescendant(
                            AllOf.allOf(
                                ViewMatchers.withId(R.id.tag_posts),
                                ViewMatchers.isDisplayed()
                            )
                        )
                    )
                )
            )


        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(
                    test_item,
                    ViewActions.click()
                )
            )

    }
}