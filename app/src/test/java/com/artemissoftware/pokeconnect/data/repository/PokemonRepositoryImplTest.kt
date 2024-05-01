package com.artemissoftware.pokeconnect.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.data.repositories.PokemonRepositoryImpl
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import com.artemissoftware.pokeconnect.data.FakePokeApi
import com.artemissoftware.pokeconnect.data.TestMockData.pokemon
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokemonRepositoryImplTest {

    private lateinit var repository: PokemonRepositoryImpl
    private lateinit var pokeApi: FakePokeApi
    private lateinit var pokeApiSource: PokeApiSource

    @BeforeEach
    fun setUp() {
        pokeApi = FakePokeApi()
        pokeApiSource =  PokeApiSource(pokeApi = pokeApi)
        repository = PokemonRepositoryImpl(pokeApiSource = pokeApiSource)
    }

    @Test
    fun `Get pokemon, return success`() = runBlocking {

        val result = repository.getPokemon(query = "bulbasaur")
        val resource = (result as Resource.Success)

        assertThat(resource.data).isEqualTo(pokemon)
    }

    @Test
    fun `Get pokemon, return error`() = runBlocking {

        pokeApi.callShouldReturnError = true
        val result = repository.getPokemon(query = "bulbasaur")

        Assertions.assertTrue { result is Resource.Failure }
    }
}