package com.artemissoftware.pokeconnect.core.network

import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.species.SpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokedexPage(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokedexPageDto

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonDto

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") name: String
    ): SpeciesDto

    companion object{
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val PAGE_SIZE = 20
        const val DEFAULT_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
        const val DEFAULT_IMAGE_URL_EXTENSION = ".png"
    }
}