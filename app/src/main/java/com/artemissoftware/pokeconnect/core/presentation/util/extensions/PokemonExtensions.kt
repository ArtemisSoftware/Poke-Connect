package com.artemissoftware.pokeconnect.core.presentation.util.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon.pokemonPalette
import com.artemissoftware.pokeconnect.core.models.pokemon.PokemonType
import com.artemissoftware.pokeconnect.core.models.pokemon.Stat

@Composable
fun PokemonType.toType(): Pair<Color, Int> {
    return when(this) {
        PokemonType.NORMAL -> MaterialTheme.pokemonPalette.normal to R.drawable.ic_normal
        PokemonType.FIRE -> MaterialTheme.pokemonPalette.fire to R.drawable.ic_fire
        PokemonType.WATER -> MaterialTheme.pokemonPalette.water to R.drawable.ic_water
        PokemonType.ELECTRIC -> MaterialTheme.pokemonPalette.electric to R.drawable.ic_electric
        PokemonType.GRASS -> MaterialTheme.pokemonPalette.grass to R.drawable.ic_grass
        PokemonType.ICE -> MaterialTheme.pokemonPalette.ice to R.drawable.ic_ice
        PokemonType.FIGHTING -> MaterialTheme.pokemonPalette.fighting to R.drawable.ic_fighting
        PokemonType.POISON -> MaterialTheme.pokemonPalette.poison to R.drawable.ic_poison
        PokemonType.GROUND -> MaterialTheme.pokemonPalette.ground to R.drawable.ic_ground
        PokemonType.FLYING -> MaterialTheme.pokemonPalette.flying to R.drawable.ic_flying
        PokemonType.PSYCHIC -> MaterialTheme.pokemonPalette.psychic to R.drawable.ic_psychic
        PokemonType.BUG -> MaterialTheme.pokemonPalette.bug to R.drawable.ic_bug
        PokemonType.ROCK -> MaterialTheme.pokemonPalette.rock to R.drawable.ic_rock
        PokemonType.GHOST -> MaterialTheme.pokemonPalette.ghost to R.drawable.ic_ghost
        PokemonType.DRAGON -> MaterialTheme.pokemonPalette.dragon to R.drawable.ic_dragon
        PokemonType.DARK -> MaterialTheme.pokemonPalette.dark to R.drawable.ic_dark
        PokemonType.STEEL -> MaterialTheme.pokemonPalette.steel to R.drawable.ic_steel
        PokemonType.FAIRY -> MaterialTheme.pokemonPalette.fairy to R.drawable.ic_fairy
        else -> MaterialTheme.pokemonPalette.unspecified to R.drawable.ic_pokeball
    }
}
fun List<Stat>.roundToNearestIncrement(): Int {

    val number = this.map { it.value }.max()

    val increment = 100
    val remainder = number % increment

    return if (remainder == 0) {
        // If there's no remainder, it's already a multiple of the increment
        number
    } else {
        // If there is a remainder, round up to the next multiple of the increment
        number + (increment - remainder)
    }
}
