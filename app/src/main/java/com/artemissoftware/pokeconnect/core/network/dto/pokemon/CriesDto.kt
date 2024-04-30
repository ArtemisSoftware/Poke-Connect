package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class CriesDto(
    @field:Json(name = "latest")
    val latest: String? = null,
    @field:Json(name = "legacy")
    val legacy: String? = null
)