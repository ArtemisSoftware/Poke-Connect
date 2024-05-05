package com.artemissoftware.pokeconnect.features.detail

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artemissoftware.pokeconnect.MainActivity
import com.artemissoftware.pokeconnect.core.network.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest {

    @Inject
    lateinit var okHttp: OkHttpClient

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mockServer: MockWebServer

    lateinit var context: Context

    @Before
    fun setup() {
        hiltRule.inject()
        mockServer = MockWebServer()
        mockServer.start(8080)
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun stopService() {
        mockServer.shutdown()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun select_pokemon_from_pokedex_check_details() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        goToDetails()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(TestTags.DETAIL_CONTENT))

        composeTestRule
            .onNodeWithTag(TestTags.DETAIL_PANEL)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.DETAIL_CONTENT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.DETAIL_CONTENT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.getPageTabTestTag(0.toString()))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.DETAIL_PAGER)
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun select_pokemon_from_pokedex_check_details_get_error() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        goToDetails()

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE))

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE)
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    private fun goToDetails() {

        this.composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.features.pokedex.TestTags.SEARCH_BAR)
            .assertIsDisplayed()

        this.composeTestRule.waitUntilExactlyOneExists(hasTestTag(com.artemissoftware.pokeconnect.features.pokedex.TestTags.POKEDEX_GRID))

        this.composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.features.pokedex.TestTags.POKEDEX_GRID)
            .onChildAt(0)
            .assertIsDisplayed()
            .performClick()
    }
}