package com.artemissoftware.pokeconnect.features.detail.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.pokeconnect.TestInstrumentedData.types
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.features.detail.TestTags.TYPES_ROW
import com.artemissoftware.pokeconnect.features.detail.TestTags.typeChipTag
import org.junit.Rule
import org.junit.Test

class TypeSectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes are visible`() {

        composeTestRule.setContent {
            PokeConnectTheme {
                TypeSection(
                    types = types,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TYPES_ROW)
            .assertIsDisplayed()

        types.forEach { type ->
            composeTestRule
                .onNodeWithTag(typeChipTag(type.description))
                .assertIsDisplayed()
                .assertTextEquals(type.description.upperCaseFirstChar())
        }
    }
}