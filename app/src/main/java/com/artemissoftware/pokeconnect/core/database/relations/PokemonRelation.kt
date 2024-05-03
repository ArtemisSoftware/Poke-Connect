package com.artemissoftware.pokeconnect.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity

data class PokemonRelation(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val types: List<TypeEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<StatEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
        )
    val abilities: List<AbilityEntity>

)
