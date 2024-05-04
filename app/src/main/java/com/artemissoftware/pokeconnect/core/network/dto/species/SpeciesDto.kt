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

//    @Json(name = "form_descriptions")
//    val formDescriptions: List<Any> = listOf(),
//    @Json(name = "forms_switchable")
//    val formsSwitchable: Boolean = false,
//    @Json(name = "gender_rate")
//    val genderRate: Int = 0,
//    @Json(name = "genera")
//    val genera: List<Genera> = listOf(),
//    @Json(name = "generation")
//    val generation: Generation = Generation(),
//    @Json(name = "growth_rate")
//    val growthRate: GrowthRate = GrowthRate(),
//    @Json(name = "habitat")
//    val habitat: Habitat = Habitat(),
//    @Json(name = "has_gender_differences")
//    val hasGenderDifferences: Boolean = false,
//    @Json(name = "hatch_counter")
//    val hatchCounter: Int = 0,
//    @Json(name = "id")
//    val id: Int = 0,
//    @Json(name = "is_baby")
//    val isBaby: Boolean = false,
//    @Json(name = "is_legendary")
//    val isLegendary: Boolean = false,
//    @Json(name = "is_mythical")
//    val isMythical: Boolean = false,
//    @Json(name = "name")
//    val name: String = "",
//    @Json(name = "names")
//    val names: List<Name> = listOf(),
//    @Json(name = "order")
//    val order: Int = 0,
//    @Json(name = "pal_park_encounters")
//    val palParkEncounters: List<PalParkEncounter> = listOf(),
//    @Json(name = "pokedex_numbers")
//    val pokedexNumbers: List<PokedexNumber> = listOf(),
//    @Json(name = "shape")
//    val shape: Shape = Shape(),
//    @Json(name = "varieties")
//    val varieties: List<Variety> = listOf()
)