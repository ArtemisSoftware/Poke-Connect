package com.artemissoftware.pokeconnect.features.detail.composables.pages

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
internal fun AboutPage(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2)
    ) {
        if(pokemon.description.isNotEmpty()) {
            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Description(
                modifier = Modifier.weight(0.5F),
                title = R.string.height,
                text = pokemon.height.toString(),
            )
            Description(
                modifier = Modifier.weight(0.5F),
                title = R.string.weight,
                text = pokemon.weight.toString(),
            )
        }

        if(pokemon.abilities.isNotEmpty()) {
            Description(
                title = R.string.abilities,
                text = pokemon.abilities.joinToString(separator = ", "),
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }
}

@Composable
private fun Description(
    @StringRes title: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
    ) {
        Text(
            text = stringResource(id = title) + ": ",
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}


@ThemePreviews
@Composable
private fun AboutPagePreview() {
    PokeConnectTheme {
        AboutPage(
            pokemon = PreviewData.pokemon,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}