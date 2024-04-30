package com.artemissoftware.pokeconnect.features

import com.artemissoftware.pokeconnect.core.models.PokedexEntry

object PreviewData {

    val pokedexEntries = listOf(
        PokedexEntry(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        PokedexEntry(
            id = 4,
            name = "Squirtle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
        ),
        PokedexEntry(
            id = 239,
            name = "Elekid",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/239.png"
        ),
    )
}