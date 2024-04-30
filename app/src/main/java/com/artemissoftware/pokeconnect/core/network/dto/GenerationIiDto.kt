package com.artemissoftware.pokeconnect.core.network.dto


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.CrystalDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.GoldDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.SilverDto
import com.squareup.moshi.Json

data class GenerationIiDto(
    @field:Json(name = "crystal")
    val crystal: CrystalDto = CrystalDto(),
    @field:Json(name = "gold")
    val gold: GoldDto = GoldDto(),
    @field:Json(name = "silver")
    val silver: SilverDto = SilverDto()
)