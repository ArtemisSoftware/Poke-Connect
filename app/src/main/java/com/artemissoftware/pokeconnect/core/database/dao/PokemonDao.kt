package com.artemissoftware.pokeconnect.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilityEntities: List<AbilityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(statsEntities: List<StatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(typesEntities: List<TypeEntity>)

    @Transaction
    suspend fun insert(
        pokemonEntity: PokemonEntity,
        statsEntities: List<StatEntity>,
        abilityEntities: List<AbilityEntity>,
        typesEntities: List<TypeEntity>,
    ) {
        insertPokemon(pokemonEntity = pokemonEntity)
        insertStats(statsEntities = statsEntities)
        insertAbilities(abilityEntities = abilityEntities)
        insertTypes(typesEntities = typesEntities)
    }

    @Transaction
    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun getPokemon(id: Int): PokemonRelation?

    @Transaction
    @Query("SELECT * FROM PokemonEntity WHERE id = :id OR name LIKE '%' || :name || '%' ORDER BY id ASC")
    suspend fun getPokemon(id: Int, name: String): List<PokemonRelation>

    @Query("SELECT * FROM PokemonEntity ORDER BY id ASC")
    fun getAll(): PagingSource<Int, PokemonEntity>


    @Query("SELECT * FROM PokemonEntity WHERE id = :id OR name LIKE '%' || :name || '%' ORDER BY id ASC")
    fun findPokemonByIdOrName(id: Int, name: String): PagingSource<Int, PokemonEntity>

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM PokemonEntity WHERE id = :id)")
    suspend fun doesItemExist(id: Int): Boolean
}