package com.artemissoftware.pokeconnect.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "stats",
    primaryKeys = ["pokemonId", "description"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE // If the parent is deleted, child records are deleted too
        )
    ]
)
data class StatEntity(
    val pokemonId: Int,
    val description: String,
    val value: Int
)
