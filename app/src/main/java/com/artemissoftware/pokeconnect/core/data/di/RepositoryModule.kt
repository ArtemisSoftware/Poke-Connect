package com.artemissoftware.pokeconnect.core.data.di

import com.artemissoftware.pokeconnect.core.data.repository.SearchHistoryRepository
import com.artemissoftware.pokeconnect.core.data.repository.SearchHistoryRepositoryImpl
import com.artemissoftware.pokeconnect.core.database.dao.SearchHistoryDao
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
    fun provideSearchHistoryRepository(searchHistoryDao: SearchHistoryDao): SearchHistoryRepository {
        return SearchHistoryRepositoryImpl(searchHistoryDao = searchHistoryDao)
    }
}