package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class GenerationIDto(
    @field:Json(name = "red-blue")
    val redBlue: RedBlueDto = RedBlueDto(),
    @field:Json(name = "yellow")
    val yellow: YellowDto = YellowDto()
)