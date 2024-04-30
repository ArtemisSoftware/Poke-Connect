package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class GoldDto(
    @field:Json(name = "back_default")
    val backDefault: String? = null,
    @field:Json(name = "back_shiny")
    val backShiny: String? = null,
    @field:Json(name = "front_default")
    val frontDefault: String? = null,
    @field:Json(name = "front_shiny")
    val frontShiny: String? = null,
    @field:Json(name = "front_transparent")
    val frontTransparent: String? = null,
)