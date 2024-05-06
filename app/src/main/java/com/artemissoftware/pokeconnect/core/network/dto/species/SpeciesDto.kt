package com.artemissoftware.pokeconnect.core.network.dto.species


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.UrlNameDto
import com.squareup.moshi.Json

data class SpeciesDto(
    @field:Json(name = "base_happiness")
    val baseHappiness: Int,
    @field:Json(name = "capture_rate")
    val captureRate: Int,
    @field:Json(name = "color")
    val color: UrlNameDto = UrlNameDto(),

    @field:Json(name = "egg_groups")
    val eggGroups: List<UrlNameDto> = emptyList(),
    @field:Json(name = "evolution_chain")
    val evolutionChain: EvolutionChainDto = EvolutionChainDto(),
    @field:Json(name = "evolves_from_species")
    val evolvesFromSpecies: UrlNameDto? = null,

    @field:Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryDto> = emptyList(),
)