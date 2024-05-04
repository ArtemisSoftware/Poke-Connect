package com.artemissoftware.pokeconnect.core.data.di

import com.artemissoftware.pokeconnect.core.data.repositories.FavoritesRepositoryImpl
import com.artemissoftware.pokeconnect.core.data.repositories.PokemonRepositoryImpl
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import com.artemissoftware.pokeconnect.core.domain.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(pokeApiSource: PokeApiSource, pokemonDataBase: PokemonDataBase/*, pokemonDao: PokemonDao*/): PokemonRepository {
        return PokemonRepositoryImpl(pokeApiSource = pokeApiSource, pokemonDao = pokemonDataBase.getPokemonDao())
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(pokemonDataBase: PokemonDataBase): FavoritesRepository {
        return FavoritesRepositoryImpl(pokemonDao = pokemonDataBase.getPokemonDao())
    }
}