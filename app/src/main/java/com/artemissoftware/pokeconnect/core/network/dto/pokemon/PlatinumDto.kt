package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class PlatinumDto(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String? = null,
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String? = null,
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String? = null,
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String? = null
)