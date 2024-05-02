package com.artemissoftware.pokeconnect.data

import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.models.Stat
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.AbilityDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OfficialArtworkDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OtherDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.SpritesDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.StatDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.UrlNameDto

object TestMockData {

    val pokedexEntryDto = PokedexEntryDto(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    val pokedexPageDto = PokedexPageDto(
        count = 1003,
        next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
        previous= null,
        results = listOf(
            PokedexEntryDto(
                name = "bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/",
            ),
            PokedexEntryDto(
                name = "ivysaur",
                url = "https://pokeapi.co/api/v2/pokemon/2/",
            ),
            PokedexEntryDto(
                name = "venusaur",
                url = "https://pokeapi.co/api/v2/pokemon/3/",
            )
        )
    )

    val pokedexEntry = PokedexEntry(
        id = 1,
        name = "bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    )

    val stat =  Stat(
        description = "hp",
        value = 45,
    )

    val abilities = listOf("torrent")

    val pokemon = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = listOf(stat),
        abilities = abilities
    )

    val pokemonDto = PokemonDto(
        id = 1,
        baseExperience = 265,
        height = 16,
        weight = 69,
        order = 1,
        name = "bulbasaur",
        locationAreaEncounters = "https://pokeapi.co/api/v2/pokemon/9/encounters",
        sprites = SpritesDto(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            other = OtherDto(
                officialArtwork = OfficialArtworkDto(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png",
                )
            )
        ),
        stats = listOf(
            StatDto(
                baseStat = 45,
                effort = 0,
                stat = UrlNameDto(
                    name = "hp",
                    url = "https://pokeapi.co/api/v2/stat/1/",
                )
            )
        ),
        abilities = listOf(
            AbilityDto(
                isHidden = false,
                slot = 1,
                ability = UrlNameDto(
                    name = "torrent",
                    url = "https://pokeapi.co/api/v2/ability/67/",
                )
            )
        )
    )

    fun getPokemonDtoWithArt(
        frontDefault: String,
        officialArtworkFrontDefault: String? = null,
        officialArtworkFrontShiny: String? = null,
    ): PokemonDto{
        return PokemonDto(
            id = 1,
            baseExperience = 265,
            height = 16,
            weight = 69,
            order = 1,
            name = "bulbasaur",
            locationAreaEncounters = "https://pokeapi.co/api/v2/pokemon/9/encounters",
            sprites = SpritesDto(
                frontDefault = frontDefault,
                other = OtherDto(
                    officialArtwork = OfficialArtworkDto(
                        frontDefault = officialArtworkFrontDefault,
                        frontShiny = officialArtworkFrontShiny,
                    )
                )
            ),
            stats = listOf(
                StatDto(
                    baseStat = 45,
                    effort = 0,
                    stat = UrlNameDto(
                        name = "hp",
                        url = "https://pokeapi.co/api/v2/stat/1/",
                    )
                )
            ),
            abilities = listOf(
                AbilityDto(
                    isHidden = false,
                    slot = 1,
                    ability = UrlNameDto(
                        name = "torrent",
                        url = "https://pokeapi.co/api/v2/ability/67/",
                    )
                )
            )
        )
    }

    val pokemonEntity = PokemonEntity(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        description = ""
    )

    val statEntry = StatEntity(
        pokemonId = 1,
        description = "hp",
        value = 45,
    )


    val abilityEntity = AbilityEntity(
        pokemonId = 1,
        description = "torrent",
    )

    val pokemonRelation = PokemonRelation(
        pokemon = pokemonEntity,
        stats = listOf(statEntry),
        abilities = listOf(abilityEntity)
    )
}