package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class UrlNameDto(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String? = null,
)