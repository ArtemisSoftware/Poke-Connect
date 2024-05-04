package com.artemissoftware.pokeconnect.data.fakes

import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.TestMockData.pokedexPageDto
import com.artemissoftware.pokeconnect.TestMockData.pokemonDto

class FakePokeApi : PokeApi {

    var callShouldReturnError = false

    override suspend fun getPokemonList(limit: Int, offset: Int): PokedexPageDto {
        if(callShouldReturnError){
            throw RuntimeException()
        }
        else {
            return pokedexPageDto
        }
    }

    override suspend fun getPokemon(name: String): PokemonDto {
        if(callShouldReturnError){
            throw RuntimeException()
        }
        else {
            return pokemonDto
        }
    }
}