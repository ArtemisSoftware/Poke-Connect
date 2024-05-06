package com.artemissoftware.pokeconnect.core.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providePokemonDataBase(
        @ApplicationContext context: Context,
    ): PokemonDataBase {
        return Room
            .databaseBuilder(
                context,
                PokemonDataBase::class.java,
                PokemonDataBase.DB_NAME,
            )
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDataBase) = database.getPokemonDao()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(database: PokemonDataBase) = database.getSearchHistoryDao()
}
