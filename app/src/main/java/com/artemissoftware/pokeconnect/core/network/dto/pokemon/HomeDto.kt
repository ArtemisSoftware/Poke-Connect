package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class HomeDto(
    @field:Json(name = "front_default")
    val frontDefault: String? = null,
    @field:Json(name = "front_female")
    val frontFemalet: String? = null,
    @field:Json(name = "front_shiny")
    val frontShinyt: String? = null,
    @field:Json(name = "front_shiny_female")
    val frontShinyFemalet: String? = null,
)