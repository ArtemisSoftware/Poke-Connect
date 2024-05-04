package com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.artemissoftware.pokeconnect.core.designsystem.Black
import com.artemissoftware.pokeconnect.core.designsystem.Bug
import com.artemissoftware.pokeconnect.core.designsystem.Dark
import com.artemissoftware.pokeconnect.core.designsystem.Dragon
import com.artemissoftware.pokeconnect.core.designsystem.Electric
import com.artemissoftware.pokeconnect.core.designsystem.Fairy
import com.artemissoftware.pokeconnect.core.designsystem.Fighting
import com.artemissoftware.pokeconnect.core.designsystem.Fire
import com.artemissoftware.pokeconnect.core.designsystem.Flying
import com.artemissoftware.pokeconnect.core.designsystem.Ghost
import com.artemissoftware.pokeconnect.core.designsystem.Grass
import com.artemissoftware.pokeconnect.core.designsystem.Ground
import com.artemissoftware.pokeconnect.core.designsystem.Ice
import com.artemissoftware.pokeconnect.core.designsystem.Normal
import com.artemissoftware.pokeconnect.core.designsystem.Poison
import com.artemissoftware.pokeconnect.core.designsystem.Psychic
import com.artemissoftware.pokeconnect.core.designsystem.Rock
import com.artemissoftware.pokeconnect.core.designsystem.Steel
import com.artemissoftware.pokeconnect.core.designsystem.Water

data class PokemonPalette(
    val normal: Color,
    val fire: Color,
    val water: Color,
    val electric: Color,
    val grass: Color,
    val ice: Color,
    val fighting: Color,
    val poison: Color,
    val ground: Color,
    val flying: Color,
    val psychic: Color,
    val bug: Color,
    val rock: Color,
    val ghost: Color,
    val dragon: Color,
    val dark: Color,
    val steel: Color,
    val fairy: Color,
    val unspecified: Color,
)

internal val pokemonPalette = PokemonPalette(
    normal = Normal,
    fire = Fire,
    water = Water,
    electric = Electric,
    grass = Grass,
    ice = Ice,
    fighting = Fighting,
    poison = Poison,
    ground = Ground,
    flying = Flying,
    psychic = Psychic,
    bug = Bug,
    rock = Rock,
    ghost = Ghost,
    dragon = Dragon,
    dark = Dark,
    steel = Steel,
    fairy = Fairy,
    unspecified = Black
)

internal val localPokemonPalette = staticCompositionLocalOf<PokemonPalette> { throw IllegalStateException("No theme installed") }

val MaterialTheme.pokemonPalette: PokemonPalette
    @Composable
    get() = localPokemonPalette.current