package com.artemissoftware.pokeconnect.core.data.di

import com.artemissoftware.pokeconnect.core.data.repositories.FavoritesRepositoryImpl
import com.artemissoftware.pokeconnect.core.data.repositories.PokedexRepositoryImpl
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.domain.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.core.domain.repositories.PokedexRepository
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
    fun providePokemonRepository(pokeApiSource: PokeApiSource, pokemonDataBase: PokemonDataBase, pokemonDao: PokemonDao): PokedexRepository {
        return PokedexRepositoryImpl(pokeApiSource = pokeApiSource, pokemonDao = pokemonDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(pokemonDataBase: PokemonDataBase): FavoritesRepository {
        return FavoritesRepositoryImpl(pokemonDao = pokemonDataBase.getPokemonDao())
    }
}