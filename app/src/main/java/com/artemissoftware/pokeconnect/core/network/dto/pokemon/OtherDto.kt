package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class OtherDto(
    @field:Json(name = "dream_world")
    val dreamWorld: DreamWorldDto = DreamWorldDto(),
    @field:Json(name = "home")
    val home: HomeDto = HomeDto(),
    @field:Json(name = "official-artwork")
    val officialArtwork: OfficialArtworkDto = OfficialArtworkDto(),
    @field:Json(name = "showdown")
    val showdown: ShowdownDto = ShowdownDto()
)