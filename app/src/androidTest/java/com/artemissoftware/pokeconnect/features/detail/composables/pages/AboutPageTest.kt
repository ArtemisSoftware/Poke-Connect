package com.artemissoftware.pokeconnect.features.detail.composables.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.TestInstrumentedData.getPokemon
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.features.detail.composables.TestTags
import org.junit.Rule
import org.junit.Test

class AboutPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes on AboutPage when pokemon has description`() {

        val description = "I am bulbasaur and I am cute"
        val pokemon = getPokemon(description)
        var height = ""
        var weight = ""
        var abilities = ""

        composeTestRule.setContent {
            height = stringResource(id = R.string.height_label)
            weight = stringResource(id = R.string.weight_label)
            abilities = stringResource(id = R.string.abilities_label)

            PokeConnectTheme {
                AboutPage(
                    pokemon = pokemon,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_DESCRIPTION)
            .assertIsDisplayed()
            .assertTextEquals(description)

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_HEIGHT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(height)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(pokemon.height.toString())
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_WEIGHT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(weight)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(pokemon.weight.toString())
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_ABILITIES)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(abilities)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(pokemon.getAbilitiesFormatted())
            .assertIsDisplayed()
    }

    @Test
    fun `Assert all nodes on AboutPage when pokemon has no description and no abilities`() {

        var height = ""
        var weight = ""
        val pokemon = getPokemon(currentAbilities = emptyList())

        composeTestRule.setContent {
            height = stringResource(id = R.string.height_label)
            weight = stringResource(id = R.string.weight_label)
            PokeConnectTheme {
                AboutPage(
                    pokemon = getPokemon(),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_DESCRIPTION)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_HEIGHT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(height)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(pokemon.height.toString())
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_WEIGHT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(weight)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(pokemon.weight.toString())
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTags.ABOUT_PAGE_POKEMON_ABILITIES)
            .assertIsDisplayed()
    }
}