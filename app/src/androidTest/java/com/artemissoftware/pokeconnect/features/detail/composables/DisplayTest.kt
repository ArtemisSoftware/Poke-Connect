package com.artemissoftware.pokeconnect.features.detail.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.pokeconnect.TestInstrumentedData.getPokemon
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.features.detail.TestTags
import org.junit.Rule
import org.junit.Test

class DisplayTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes on Display`() {

        val pokemon = getPokemon()

        composeTestRule.setContent {
            PokeConnectTheme {
                Display(
                    modifier = Modifier.fillMaxWidth(),
                    pokemon = pokemon,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TestTags.DISPLAY_COLUMN)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.DISPLAY_POKEMON_ID)
            .assertIsDisplayed()
            .assertTextEquals(pokemon.id.toFormattedNumber())

        composeTestRule
            .onNodeWithTag(TestTags.DISPLAY_POKEMON_IMAGE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.DISPLAY_POKEMON_TYPE)
            .assertIsDisplayed()
    }
}