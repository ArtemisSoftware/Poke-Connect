package com.artemissoftware.pokeconnect.core.network.source

import com.artemissoftware.pokeconnect.core.network.HandleApi
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.species.SpeciesDto
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

    suspend fun getSpecies(query: String): SpeciesDto {
        return HandleApi.safeApiCall { pokeApi.getPokemonSpecies(name = query) }
    }
}
