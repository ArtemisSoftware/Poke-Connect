package com.artemissoftware.pokeconnect.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val description: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
)
