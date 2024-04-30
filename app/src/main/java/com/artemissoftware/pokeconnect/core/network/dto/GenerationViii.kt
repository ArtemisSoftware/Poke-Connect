package com.artemissoftware.pokeconnect.core.network.dto


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.IconsDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationViii(
    @Json(name = "icons")
    val icons: IconsDto = IconsDto()
)