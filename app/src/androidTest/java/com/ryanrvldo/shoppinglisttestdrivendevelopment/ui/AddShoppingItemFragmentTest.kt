package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R
import com.ryanrvldo.shoppinglisttestdrivendevelopment.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var navController: NavController


    @Before
    fun setup() {
        hiltRule.inject()
        navController = mock(NavController::class.java)
    }

    @Test
    fun pressBackButton_popBackStack() {
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }


    @Test
    fun clickShoppingImageView_navigateToImagePickFragment() {
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.ivShoppingImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        verify(navController).navigate(AddShoppingItemFragmentDirections.actionAddShoppingItemToImagePick())
    }
}