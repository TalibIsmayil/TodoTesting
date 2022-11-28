package com.scrumlaunch.todotesting.presentation.folders

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.scrumlaunch.todotesting.R
import com.scrumlaunch.todotesting.launchFragmentInHiltContainer
import com.scrumlaunch.todotesting.presentation.addfolder.AddFolderFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FoldersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun testNavigateToAddFolder() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<FoldersFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.fab)).perform(click())

        Mockito.verify(navController).navigate(R.id.action_to_addFolderFragment)
    }

    @Test
    fun testAddFolder() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AddFolderFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.edtTitle)).perform(replaceText("Title"))
        onView(withId(R.id.edtPassword)).perform(replaceText("Title"))
        onView(withId(R.id.btnSave)).perform(click())


        Mockito.verify(navController).navigateUp()
    }


}