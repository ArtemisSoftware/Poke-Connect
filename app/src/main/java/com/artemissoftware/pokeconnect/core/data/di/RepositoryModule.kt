package com.artemissoftware.pokeconnect.core.data.di

import com.artemissoftware.pokeconnect.core.data.repositories.PokemonRepositoryImpl
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
    fun providePokemonRepository(pokeApiSource: PokeApiSource): PokemonRepository {
        return PokemonRepositoryImpl(pokeApiSource = pokeApiSource)
    }
}