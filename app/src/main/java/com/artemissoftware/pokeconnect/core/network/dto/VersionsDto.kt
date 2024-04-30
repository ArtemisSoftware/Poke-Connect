package com.artemissoftware.pokeconnect.core.network.dto


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.GenerationIDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.GenerationIiDto
import com.squareup.moshi.Json

data class VersionsDto(
    @field:Json(name = "generation-i")
    val generationI: GenerationIDto = GenerationIDto(),
    @field:Json(name = "generation-ii")
    val generationIi: GenerationIiDto = GenerationIiDto(),
    @field:Json(name = "generation-iii")
    val generationIii: GenerationIii = GenerationIii(),
    @field:Json(name = "generation-iv")
    val generationIv: GenerationIv = GenerationIv(),
    @field:Json(name = "generation-v")
    val generationV: GenerationV = GenerationV(),
    @field:Json(name = "generation-vi")
    val generationVi: GenerationVi = GenerationVi(),
    @field:Json(name = "generation-vii")
    val generationVii: GenerationVii = GenerationVii(),
    @field:Json(name = "generation-viii")
    val generationViii: GenerationViii = GenerationViii()
)