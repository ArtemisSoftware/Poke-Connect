package com.artemissoftware.pokeconnect.core.network.source

import com.artemissoftware.pokeconnect.core.network.HandleApi
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import javax.inject.Inject

class PokeApiSource @Inject constructor(
    private val pokeApi: PokeApi,
) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokedexPageDto {
        return HandleApi.safeApiCall { pokeApi.getPokemonList(limit = limit, offset = offset) }
    }

    suspend fun getPokemon(query: String): PokemonDto {
        return HandleApi.safeApiCall { pokeApi.getPokemon(name = query) }
    }
}
