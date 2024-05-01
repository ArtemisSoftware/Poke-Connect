package com.artemissoftware.pokeconnect.core.models

data class Pokemon(
    val id: Int,
    val description: String = "", // TODO: alterar depois
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val stats: List<Stat> = emptyList(),
    val abilities: List<String> = emptyList()
)
