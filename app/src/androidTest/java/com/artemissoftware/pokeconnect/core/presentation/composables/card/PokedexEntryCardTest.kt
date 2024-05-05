package com.artemissoftware.pokeconnect.core.presentation.composables.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.artemissoftware.pokeconnect.TestInstrumentedData
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_CARD
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_CARD_CONTENT
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_ID
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_IMAGE
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_NAME
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_CARD
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_ID
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_NAME
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class PokedexEntryCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes on Pokedex when pokemon is available`() {

        var hasClickedOnCard = false
        val pokedexEntry = TestInstrumentedData.pokedexEntry

        composeTestRule.setContent {
            PokeConnectTheme {
                PokedexEntryCard(
                    pokedexEntry = pokedexEntry,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        hasClickedOnCard = true
                    },
                )
            }
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_CARD)
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_CARD_CONTENT, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_IMAGE, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_NAME, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(pokedexEntry.name.upperCaseFirstChar())

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_ID, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(pokedexEntry.id.toFormattedNumber())

        Assert.assertTrue(hasClickedOnCard)
    }


    @Test
    fun `Assert all nodes on Pokedex when card is in shimmer mode`() {
        composeTestRule.setContent {
            PokeConnectTheme {
                ShimmerPokedexEntryCard(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_SHIMMER_CARD)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_CARD_CONTENT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_IMAGE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_SHIMMER_NAME)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(POKEDEX_ENTRY_SHIMMER_ID)
            .assertIsDisplayed()
    }
}