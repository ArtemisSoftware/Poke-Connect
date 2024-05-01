package com.artemissoftware.pokeconnect.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity

@Database(
    entities = [
        PokemonEntity::class,
        StatEntity::class,
        AbilityEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object{
        const val DB_NAME = "pokemon_db"
    }
}