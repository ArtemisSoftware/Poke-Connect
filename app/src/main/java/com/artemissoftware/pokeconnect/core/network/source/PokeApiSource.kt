package com.artemissoftware.pokeconnect.core.network.source

import com.artemissoftware.pokeconnect.core.network.HandleApi
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.PokemonLisDto

class PokeApiSource constructor(
    private val pokeApi: PokeApi,
) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonLisDto {
        return HandleApi.safeApiCall { pokeApi.getPokemonList(limit = limit, offset = offset) }
    }

    suspend fun getPokemon(query: String): PokemonDto {
        return HandleApi.safeApiCall { pokeApi.getPokemon(name = query) }
    }
}
