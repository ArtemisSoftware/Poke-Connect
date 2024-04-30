package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class OfficialArtworkDto(
    @field:Json(name = "front_default")
    val frontDefault: String? = null,
    @field:Json(name = "front_shiny")
    val frontShiny: String? = null,
)