package com.artemissoftware.pokeconnect.features.detail.composables.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.TestInstrumentedData.stats
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.features.detail.composables.TestTags
import org.junit.Rule
import org.junit.Test

class StatsPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes on StatsPage`() {

        composeTestRule.setContent {
            PokeConnectTheme {
                StatsPage(
                    stats = stats,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        val statsList = composeTestRule
            .onNodeWithTag(TestTags.STATS_LIST)

        statsList
            .assertIsDisplayed()
            .onChildren()
            .assertCountEquals(stats.size)

        stats.forEachIndexed { index, stat ->
            statsList.performScrollToIndex(index)

            composeTestRule
                .onNodeWithTag(TestTags.statTag(stat.description))
                .assertIsDisplayed()

            composeTestRule
                .onNodeWithTag(TestTags.statTitleTag(stat.abbreviation))
                .assertIsDisplayed()
                .assertTextEquals(stat.abbreviation)

            composeTestRule
                .onNodeWithTag(TestTags.statValueTag(stat.abbreviation))
                .assertIsDisplayed()
                .assertTextEquals(stat.value.toString())

            composeTestRule
                .onNodeWithTag(TestTags.statProgressTag(stat.abbreviation))
                .assertIsDisplayed()
        }
    }

    @Test
    fun `Assert all nodes on StatsPage when there are no stats`() {

        var text = ""
        composeTestRule.setContent {
            text = stringResource(id = R.string.no_stats_available)

            PokeConnectTheme {
                StatsPage(
                    stats = emptyList(),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule
            .onNodeWithText(text)
            .assertIsDisplayed()
    }
}