package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class FlavorTextEntriesDto(
    @field:Json(name = "flavor_text")
    val flavorText: String = "",
    @field:Json(name = "language")
    val language: LanguageDto = LanguageDto(),
    @field:Json(name = "version")
    val version: VersionDto = VersionDto()
)