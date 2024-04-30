package com.artemissoftware.pokeconnect.data

import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.network.dto.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.dto.PokemonListDto

object TestMockData {

    val pokedexEntryDto = PokedexEntryDto(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    val pokemonListDto = PokemonListDto(
        count = 1003,
        next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
        previous= null,
        results = listOf(
            PokedexEntryDto(
                name = "bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/",
            ),
            PokedexEntryDto(
                name = "ivysaur",
                url = "https://pokeapi.co/api/v2/pokemon/2/",
            ),
            PokedexEntryDto(
                name = "venusaur",
                url = "https://pokeapi.co/api/v2/pokemon/3/",
            )
        )
    )

    val pokedexEntry = PokedexEntry(
        id = 1,
        name = "bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + 1 + ".png",
    )
}