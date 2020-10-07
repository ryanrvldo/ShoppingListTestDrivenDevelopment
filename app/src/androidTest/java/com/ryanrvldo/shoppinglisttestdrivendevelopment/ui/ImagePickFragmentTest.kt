package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R
import com.ryanrvldo.shoppinglisttestdrivendevelopment.adapter.ImageAdapter
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.FakeShoppingRepositoryAndroidTest
import com.ryanrvldo.shoppinglisttestdrivendevelopment.getOrAwaitValue
import com.ryanrvldo.shoppinglisttestdrivendevelopment.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject


@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class ImagePickFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    private lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()
        navController = mock(NavController::class.java)
    }

    @Test
    fun clickImage_popBackStackAndSetImageUrl() {
        val imageUrl = "TEST"
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<ImagePickFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            imageAdapter.images = listOf(imageUrl)
            viewModel = testViewModel
        }

        onView(withId(R.id.rvImages))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                    0,
                    click()
                )
            )

        verify(navController).popBackStack()
        assertThat(testViewModel.currentImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)
    }
}