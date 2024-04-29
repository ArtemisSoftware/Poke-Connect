package com.artemissoftware.pokeconnect.core.network

import com.artemissoftware.pokeconnect.core.network.dto.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.PokemonLisDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonLisDto

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): PokemonDto

    companion object{
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val PAGE_SIZE = 20
    }
}