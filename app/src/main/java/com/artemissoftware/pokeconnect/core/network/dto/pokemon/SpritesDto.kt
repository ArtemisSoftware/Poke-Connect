package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class SpritesDto(
    @field:Json(name = "back_default")
    val backDefault: String? = null,
    @field:Json(name = "back_female")
    val backFemale: String? = null,
    @field:Json(name = "back_shiny")
    val backShiny: String? = null,
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String? = null,
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String? = null,
    @field:Json(name = "front_shiny")
    val frontShiny: String? = null,
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String? = null,
    @field:Json(name = "other")
    val other: OtherDto = OtherDto(),
//    @field:Json(name = "versions")
//    val versions: VersionsDto = VersionsDto()
)