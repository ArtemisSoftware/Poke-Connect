package com.artemissoftware.pokeconnect.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artemissoftware.pokeconnect.core.database.converters.DateTypeConverter
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.database.dao.SearchHistoryDao
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        StatEntity::class,
        AbilityEntity::class,
        TypeEntity::class,
        SearchHistoryEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateTypeConverter::class)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    abstract fun getSearchHistoryDao(): SearchHistoryDao

    companion object{
        const val DB_NAME = "pokemon_db"
    }
}