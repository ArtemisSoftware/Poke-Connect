package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class DreamWorldDto(
    @field:Json(name = "front_default")
    val frontDefault: String? = null,
    @field:Json(name = "front_female")
    val frontFemale: String? = null
)