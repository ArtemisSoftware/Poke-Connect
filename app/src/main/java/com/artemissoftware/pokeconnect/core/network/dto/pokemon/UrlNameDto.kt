package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class UrlNameDto(
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "url")
    val url: String? = null,
)