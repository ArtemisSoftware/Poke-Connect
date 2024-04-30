package com.artemissoftware.pokeconnect.data

import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.PokemonListDto
import com.artemissoftware.pokeconnect.data.TestMockData.pokemonListDto

class FakePokeApi : PokeApi {

    var callShouldReturnError = false

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListDto {
        if(callShouldReturnError){
            throw RuntimeException()
        }
        else {
            return pokemonListDto
        }
    }

    override suspend fun getPokemon(name: String): PokemonDto {
        TODO("Not yet implemented")
    }
}