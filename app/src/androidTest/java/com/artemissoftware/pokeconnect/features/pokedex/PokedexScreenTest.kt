package com.artemissoftware.pokeconnect.features.pokedex

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artemissoftware.pokeconnect.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PokedexScreenTest {

    @get:Rule(order = 0)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_authentication_login_success() {

        composeTestRule.onNodeWithTag("enter email")
            .assertIsDisplayed()
    }
}