package com.artemissoftware.pokeconnect.data.favorites.di

import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.data.favorites.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.data.favorites.repositories.FavoritesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesRepositoryModule {

    @Provides
    @Singleton
    fun provideFavoritesRepository(pokemonDao: PokemonDao): FavoritesRepository {
        return FavoritesRepositoryImpl(pokemonDao = pokemonDao)
    }
}