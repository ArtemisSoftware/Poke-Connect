package com.artemissoftware.pokeconnect.features.pokedex

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
import com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE
import com.artemissoftware.pokeconnect.features.pokedex.TestTags.POKEDEX_GRID
import com.artemissoftware.pokeconnect.features.pokedex.TestTags.SEARCH_BAR
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
class PokedexScreenTest {

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
    fun load_pokedex_check_grid_has_data() {

         mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        composeTestRule
            .onNodeWithTag(SEARCH_BAR)
            .assertIsDisplayed()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(POKEDEX_GRID))

        val pokedex = composeTestRule
            .onNodeWithTag(POKEDEX_GRID)

        pokedex
            .assertIsDisplayed()

        pokedex
            .onChildAt(0)
            .assertIsDisplayed()
            .performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun load_pokedex_with_error_check_error_screen() {

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()

        composeTestRule
            .onNodeWithTag(SEARCH_BAR)
            .assertIsDisplayed()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(PLACE_HOLDER_PAGE))

        composeTestRule
            .onNodeWithTag(PLACE_HOLDER_PAGE)
            .assertIsDisplayed()
    }

}