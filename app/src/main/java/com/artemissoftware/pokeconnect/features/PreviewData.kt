package com.artemissoftware.pokeconnect.features

import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.models.Stat
import com.artemissoftware.pokeconnect.features.detail.DetailState

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

    val pokemon = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        description = "A strange seed was planted on its back at birth. The plant sprouts and grows with this POKéMON.",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = listOf(
            Stat(
                description = "hp",
                value = 45,
            )
        ),
        abilities = listOf("overgrow", "chlorophyll")
    )

    val detailState = DetailState(
        selectedTabIndex = 0,
        pokemon = pokemon
    )
}