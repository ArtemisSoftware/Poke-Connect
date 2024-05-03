package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class RubySapphireDto(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = ""
)