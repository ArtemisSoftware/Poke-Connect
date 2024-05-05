package com.artemissoftware.pokeconnect.data.repository

import com.artemissoftware.pokeconnect.core.data.repositories.PokedexRepositoryImpl
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import com.artemissoftware.pokeconnect.data.fakes.FakePokeApi
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
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
    fun `Get pokemon, return pokemon from database with success`() = runBlocking {

//        coEvery { pokemonDao.getPokemon(any()) } returns pokemonRelation
//
//        val result = repository.getPokemon(query = "bulbasaur")
//
//        coVerify { pokemonDao.getPokemon(any()) }
//
//        val resource = (result as Resource.Success)
//        assertThat(resource.data).isEqualTo(pokemonFromDb)
    }

    @Test
    fun `Get pokemon, return pokemon from api with success`() = runBlocking {

//        coEvery { pokemonDao.getPokemon(any()) } returns null
//
//        val result = repository.getPokemon(query = "bulbasaur")
//
//        coVerify(exactly = 1) { pokemonDao.getPokemon(any()) }
//
//        val resource = (result as Resource.Success)
//        assertThat(resource.data).isEqualTo(pokemonFromApi)
    }

    @Test
    fun `Get pokemon, that does not exist on database check api and return error`() = runBlocking {

//        coEvery { pokemonDao.getPokemon(any()) } returns null
//        pokeApi.callShouldReturnError = true
//
//        val result = repository.getPokemon(query = "bulbasaur")
//        coVerify(exactly = 1) { pokemonDao.getPokemon(any()) }
//
//        Assertions.assertTrue { result is Resource.Failure }
    }
}