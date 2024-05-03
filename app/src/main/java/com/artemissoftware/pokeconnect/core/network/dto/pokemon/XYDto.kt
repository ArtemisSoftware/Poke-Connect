package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class XYDto(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String? = null,
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String? = null
)