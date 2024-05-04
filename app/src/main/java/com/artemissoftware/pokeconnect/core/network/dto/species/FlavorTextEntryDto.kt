package com.artemissoftware.pokeconnect.core.network.dto.species


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.UrlNameDto
import com.squareup.moshi.Json

data class FlavorTextEntryDto(
    @field:Json(name = "flavor_text")
    val flavorText: String = "",
    @field:Json(name = "language")
    val language: UrlNameDto = UrlNameDto(),
    @field:Json(name = "version")
    val version: UrlNameDto = UrlNameDto()
)