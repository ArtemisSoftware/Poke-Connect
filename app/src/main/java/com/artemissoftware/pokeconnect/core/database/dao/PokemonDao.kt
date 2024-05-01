package com.artemissoftware.pokeconnect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilityEntities: List<AbilityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(statsEntities: List<StatEntity>)

    @Transaction
    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun getPokemon(id: Int): PokemonRelation

    @Transaction
    suspend fun insert(pokemonEntity: PokemonEntity, statsEntities: List<StatEntity>, abilityEntities: List<AbilityEntity>) {
        insert(pokemonEntity = pokemonEntity)
        insertStats(statsEntities = statsEntities)
        insertAbilities(abilityEntities = abilityEntities)
    }

    @Query("DELETE FROM PokemonEntity WHERE id = :id")
    suspend fun delete(id: Int)
}