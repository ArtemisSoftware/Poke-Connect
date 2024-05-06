package com.artemissoftware.pokeconnect.data.pokedex.di

import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepository
import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexRepositoryModule {

    @Provides
    @Singleton
    fun providePokedexRepository(pokeApiSource: PokeApiSource, pokemonDao: PokemonDao): PokedexRepository {
        return PokedexRepositoryImpl(pokeApiSource = pokeApiSource, pokemonDao = pokemonDao)
    }
}