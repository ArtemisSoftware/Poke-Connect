package com.artemissoftware.pokeconnect.data

import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.network.dto.PokedexEntryDto

object TestMockData {

    val pokedexEntryDto = PokedexEntryDto(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    val pokedexEntry = PokedexEntry(
        id = 1,
        name = "bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + 1 + ".png",
    )
}