package com.artemissoftware.pokeconnect.data.pokedex.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.TestMockData.description
import com.artemissoftware.pokeconnect.TestMockData.getPokemon
import com.artemissoftware.pokeconnect.TestMockData.pokemonRelation
import com.artemissoftware.pokeconnect.core.data.fakes.FakePokeApi
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokedexRepositoryImplTest {

    private lateinit var repository: PokedexRepositoryImpl
    private lateinit var pokeApi: FakePokeApi
    private lateinit var pokeApiSource: PokeApiSource
    private lateinit var pokemonDao: PokemonDao

    @BeforeEach
    fun setUp() {
        pokemonDao = mockk()
        pokeApi = FakePokeApi()
        pokeApiSource =  PokeApiSource(pokeApi = pokeApi)
        repository = PokedexRepositoryImpl(pokeApiSource = pokeApiSource, pokemonDao = pokemonDao)
    }

    @Test
    fun `Search pokedex for pokemon, returns a list of pokemons from database with success`() = runBlocking {

        coEvery { pokemonDao.getPokemon(any(), any()) } returns listOf(pokemonRelation)

        val result = repository.searchPokedex(query = "bulbasaur")

        coVerify { pokemonDao.getPokemon(any(), any()) }

        val resource = (result as Resource.Success)
        assertThat(resource.data).isEqualTo(listOf(getPokemon(isFavorite = true)))
    }

    @Test
    fun `Search pokedex for pokemon, return pokemon from api with success`() = runBlocking {

        coEvery { pokemonDao.getPokemon(any(), any()) } returns emptyList()

        val result = repository.searchPokedex(query = "bulbasaur")

        coVerify(exactly = 1) { pokemonDao.getPokemon(any(), any()) }

        val resource = (result as Resource.Success)
        assertThat(resource.data).isEqualTo(listOf(getPokemon(description)))
    }

    @Test
    fun `Search pokedex for pokemon that does not exist on database, api return error`() = runBlocking {

        coEvery { pokemonDao.getPokemon(any(), any()) } returns emptyList()
        pokeApi.callShouldReturnError = true

        val result = repository.searchPokedex(query = "bulbasaur")

        coVerify(exactly = 1) { pokemonDao.getPokemon(any(), any()) }

        Assertions.assertTrue { result is Resource.Failure }
    }

    @Test
    fun `Search pokedex for pokemon that does not exist on database, api for pokemon is successful but species return error`() = runBlocking {

        coEvery { pokemonDao.getPokemon(any(), any()) } returns emptyList()
        pokeApi.callShouldReturnSpeciesError = true

        val result = repository.searchPokedex(query = "bulbasaur")

        coVerify(exactly = 1) { pokemonDao.getPokemon(any(), any()) }

        val resource = (result as Resource.Success)
        assertThat(resource.data).isEqualTo(listOf(getPokemon()))
    }
}