package com.artemissoftware.pokeconnect.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import com.artemissoftware.pokeconnect.core.database.di.DataBaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DataBaseModule::class])
object TestDataBaseModule {

    @Provides
    fun provideInMemoryDb(@ApplicationContext context: Context): PokemonDataBase =
        Room.inMemoryDatabaseBuilder(context, PokemonDataBase::class.java)
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDataBase) = database.getPokemonDao()
}
