package com.leafy.filmgallery.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.leafy.filmgallery.R
import com.leafy.filmgallery.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.menuTvShow)).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadMovieDescription() {
        // Get a string
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        // Make sure the data has been successfully received
        onView(withId(R.id.tvDate)).check(matches(not(withText(""))))
        onView(withId(R.id.tvCategory)).check(matches(not(withText(""))))
        onView(withId(R.id.tvDescription)).check(matches(not(withText(""))))
    }

    @Test
    fun loadTvShowDescription() {
        onView(withId(R.id.menuTvShow)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        // Make sure the data has been successfully received
        onView(withId(R.id.tvDate)).check(matches(not(withText(""))))
        onView(withId(R.id.tvCategory)).check(matches(not(withText(""))))
        onView(withId(R.id.tvDescription)).check(matches(not(withText(""))))
    }

    @Test
    fun setFavoriteMovie() {
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.actionFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.menuFavorite)).perform(click())
        onView(withId(R.id.rvFavMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvDate)).check(matches(not(withText(""))))
        onView(withId(R.id.tvCategory)).check(matches(not(withText(""))))
        onView(withId(R.id.tvDescription)).check(matches(not(withText(""))))
        onView(withId(R.id.actionFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun setFavoriteTvShow() {
        onView(withId(R.id.menuTvShow)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.actionFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.menuFavorite)).perform(click())
        onView(allOf(withText(R.string.tvShow), isDescendantOfA(withId(R.id.tabLayout)))).perform(click())
        onView(withId(R.id.rvFavTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvDate)).check(matches(not(withText(""))))
        onView(withId(R.id.tvCategory)).check(matches(not(withText(""))))
        onView(withId(R.id.tvDescription)).check(matches(not(withText(""))))
        onView(withId(R.id.actionFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun setSortOrder() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.newest)).perform(click())
        onView(withId(R.id.menuTvShow)).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.oldest)).perform(click())
        onView(withId(R.id.menuMovie)).perform(click())
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.title)).perform(click())
    }
}