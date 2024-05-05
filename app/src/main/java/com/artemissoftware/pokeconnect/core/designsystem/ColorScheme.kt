package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemissoftware.pokeconnect.core.designsystem.composables.ColorDescription
import com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon.pokemonPalette

@ThemePreviews
@Composable
private fun PalettePreview() {
    PokeConnectTheme {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 3),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1_5),
        ) {
            item {
                ColorDescription(
                    text = "favorite",
                    background = MaterialTheme.palette.favorite,
                )
            }
            item {
                ColorDescription(
                    text = "fire",
                    background = MaterialTheme.palette.notFavorite,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PokemonPalettePreview() {
    PokeConnectTheme {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 3),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1_5),
        ) {
            item {
                ColorDescription(
                    text = "normal",
                    background = MaterialTheme.pokemonPalette.normal,
                )
            }
            item {
                ColorDescription(
                    text = "fire",
                    background = MaterialTheme.pokemonPalette.fire,
                )
            }
            item {
                ColorDescription(
                    text = "water",
                    background = MaterialTheme.pokemonPalette.water,
                )
            }

            item {
                ColorDescription(
                    text = "electric",
                    background = MaterialTheme.pokemonPalette.electric,
                )
            }
            item {
                ColorDescription(
                    text = "grass",
                    background = MaterialTheme.pokemonPalette.grass,
                )
            }
            item {
                ColorDescription(
                    text = "ice",
                    background = MaterialTheme.pokemonPalette.ice,
                )
            }

            item {
                ColorDescription(
                    text = "fighting",
                    background = MaterialTheme.pokemonPalette.fighting,
                )
            }
            item {
                ColorDescription(
                    text = "poison",
                    background = MaterialTheme.pokemonPalette.poison,
                )
            }
            item {
                ColorDescription(
                    text = "ground",
                    background = MaterialTheme.pokemonPalette.ground,
                )
            }

            item {
                ColorDescription(
                    text = "flying",
                    background = MaterialTheme.pokemonPalette.flying,
                )
            }
            item {
                ColorDescription(
                    text = "psychic",
                    background = MaterialTheme.pokemonPalette.psychic,
                )
            }
            item {
                ColorDescription(
                    text = "bug",
                    background = MaterialTheme.pokemonPalette.bug,
                )
            }

            item {
                ColorDescription(
                    text = "rock",
                    background = MaterialTheme.pokemonPalette.rock,
                )
            }
            item {
                ColorDescription(
                    text = "ghost",
                    background = MaterialTheme.pokemonPalette.ghost,
                )
            }
            item {
                ColorDescription(
                    text = "dragon",
                    background = MaterialTheme.pokemonPalette.dragon,
                )
            }

            item {
                ColorDescription(
                    text = "dark",
                    background = MaterialTheme.pokemonPalette.dark,
                )
            }
            item {
                ColorDescription(
                    text = "steel",
                    background = MaterialTheme.pokemonPalette.steel,
                )
            }
            item {
                ColorDescription(
                    text = "fairy",
                    background = MaterialTheme.pokemonPalette.fairy,
                )
            }

            item {
                ColorDescription(
                    text = "unspecified",
                    background = MaterialTheme.pokemonPalette.unspecified,
                )
            }
        }
    }
}
