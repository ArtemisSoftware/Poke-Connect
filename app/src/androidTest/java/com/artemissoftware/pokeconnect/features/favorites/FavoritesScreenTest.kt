package com.artemissoftware.pokeconnect.features.favorites

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artemissoftware.pokeconnect.MainActivity
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.network.MockServerDispatcher
import com.artemissoftware.pokeconnect.features.pokedex.TestTags
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
class FavoritesScreenTest {

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
    fun select_a_pokemon_on_pokedex_go_to_details_make_it_favorite_check_the_new_favorite_on_favorite_list() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        goToDetailsToAddFavorite()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(TestTag.FAVORITES_HEADER))

        val pokedex = composeTestRule
            .onNodeWithTag(TestTags.POKEDEX_GRID)

        pokedex
            .assertIsDisplayed()

        pokedex
            .onChildAt(0)
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun go_to_favorites_with_no_favorites_check_favorites_is_empty() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        goToFavorites()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE))

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER__PAGE_TEXT)
            .assertIsDisplayed()
            .assertTextEquals(context.getString(R.string.no_favorites_found))
    }

    @OptIn(ExperimentalTestApi::class)
    private fun goToDetailsToAddFavorite(){
        composeTestRule
            .onNodeWithTag(TestTags.SEARCH_BAR)
            .assertIsDisplayed()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(TestTags.POKEDEX_GRID))

        composeTestRule
            .onNodeWithTag(TestTags.POKEDEX_GRID)
            .onChildAt(0)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(com.artemissoftware.pokeconnect.features.detail.TestTags.DETAIL_CONTENT))

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.features.detail.TestTags.FAVORITE_BUTTON)
            .performClick()

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.features.detail.TestTags.DETAIL_BACK_BUTTON)
            .performClick()

        goToFavorites()
    }

    @OptIn(ExperimentalTestApi::class)
    private fun goToFavorites(){
        composeTestRule
            .onNodeWithTag(TestTags.SEARCH_BAR)
            .assertIsDisplayed()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag(TestTags.POKEDEX_GRID))

        composeTestRule
            .onNodeWithTag(com.artemissoftware.pokeconnect.core.ui.TestTags.getNavBarItemTestTag(1))
            .assertIsDisplayed()
            .performClick()
    }
}