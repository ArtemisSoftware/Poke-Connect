package com.artemissoftware.pokeconnect.features.detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.shape
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.pokemon.PokemonType
import com.artemissoftware.pokeconnect.core.presentation.util.extensions.toType
import com.artemissoftware.pokeconnect.core.ui.icon.CircularIcon
import com.artemissoftware.pokeconnect.features.PreviewData
import com.artemissoftware.pokeconnect.features.detail.TestTags
import com.artemissoftware.pokeconnect.features.detail.TestTags.TYPES_ROW

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TypeSection(
    types: List<PokemonType>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        FlowRow(
            modifier = Modifier
                .testTag(tag = TYPES_ROW)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            types.forEach { type->
                val (typeColor, typeIcon) = type.toType()

                AssistChip(
                    modifier = Modifier
                        .testTag(tag = TestTags.typeChipTag(type.description)),
                    onClick = {  },
                    label = {
                        Text(
                            text = type.description.upperCaseFirstChar(),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    },
                    shape = MaterialTheme.shape.circular,
                    border = AssistChipDefaults.assistChipBorder(false),
                    colors = AssistChipDefaults.assistChipColors(containerColor = typeColor),
                    leadingIcon = {
                        CircularIcon(
                            iconSize = MaterialTheme.dimension.iconContentSize,
                            iconContentSize = MaterialTheme.dimension.iconChipSmall,
                            icon = typeIcon,
                            onClick = {},
                            tint = typeColor
                        )
                    }
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.spacing1))
            }
        }
    }
}

@ThemePreviews
@Composable
private fun TypeSectionPreview() {
    PokeConnectTheme {
        TypeSection(
            modifier = Modifier.fillMaxWidth(),
            types = PreviewData.pokemon.types,
        )
    }
}