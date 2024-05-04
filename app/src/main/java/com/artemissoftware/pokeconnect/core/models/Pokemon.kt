package com.artemissoftware.pokeconnect.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val description: String = "", // TODO: alterar depois
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val stats: List<Stat> = emptyList(),
    val abilities: List<String> = emptyList(),
    val types: List<PokemonType> = emptyList(),
): Parcelable
