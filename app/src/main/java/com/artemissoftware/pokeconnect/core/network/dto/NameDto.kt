package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class NameDto(
    @Json(name = "language")
    val language: LanguageDto = LanguageDto(),
    @Json(name = "name")
    val name: String = ""
)