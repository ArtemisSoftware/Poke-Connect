package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class GeneraDto(
    @Json(name = "genus")
    val genus: String = "",
    @Json(name = "language")
    val language: LanguageDto = LanguageDto()
)