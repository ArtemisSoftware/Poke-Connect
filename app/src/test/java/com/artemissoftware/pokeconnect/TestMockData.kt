package com.artemissoftware.pokeconnect

import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.models.PokemonType
import com.artemissoftware.pokeconnect.core.models.Stat
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexPageDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.AbilityDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OfficialArtworkDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OtherDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.SpritesDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.StatDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.TypeDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.UrlNameDto
import com.artemissoftware.pokeconnect.core.network.dto.species.EvolutionChainDto
import com.artemissoftware.pokeconnect.core.network.dto.species.FlavorTextEntryDto
import com.artemissoftware.pokeconnect.core.network.dto.species.SpeciesDto

object TestMockData {

    val description = "A strange seed was planted on its back at birth.The plant sprouts and grows with this POKéMON."

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
        abbreviation = "HP",
        description = "hp",
        value = 45,
    )

    val stats = listOf(
        stat,
        Stat(
            abbreviation = "Atk",
            description = "attack",
            value = 145,
        ),
        Stat(
            abbreviation = "SpAtk",
            description = "special-attack",
            value = 85,
        )
    )

    val abilities = listOf("torrent")
    val types = listOf(PokemonType.GRASS)

    fun getPokemon(description: String = "", isFavorite: Boolean = false): Pokemon{
        return Pokemon(
            id = 1,
            name = "bulbasaur",
            height = 16,
            weight = 69,
            isFavorite = isFavorite,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            stats = listOf(stat),
            abilities = abilities,
            types = types,
            description = description
        )
    }

    val pokemonFromApi = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = listOf(stat),
        abilities = abilities,
        types = types,
        description = "A strange seed was planted on its back at birth.The plant sprouts and grows with this POKéMON."
    )

    val pokemonFromApiWithSpeciesDescription = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = listOf(stat),
        abilities = abilities,
        types = types,
        description = description
    )

    val pokemonFromDb = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = listOf(stat),
        abilities = abilities,
        types = types,
        isFavorite = true,
        description = description
    )

    val pokemonDto = PokemonDto(
        id = 1,
        height = 16,
        weight = 69,
        name = "bulbasaur",
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
        ),
        types = listOf(
            TypeDto(
                slot = 1,
                type = UrlNameDto("grass")
            ),
        ),
        baseExperience = 265,
        order = 1,
        locationAreaEncounters = "https://pokeapi.co/api/v2/pokemon/9/encounters",
        species = UrlNameDto(name = "bulbasaur"),
    )

    val speciesDto = SpeciesDto(
        baseHappiness = 50,
        captureRate = 45,
        color = UrlNameDto(
            "green"
        ),
        eggGroups = listOf(
            UrlNameDto(
                "monster"
            )
        ),
        evolutionChain = EvolutionChainDto("https://pokeapi.co/api/v2/evolution-chain/1/"),
        flavorTextEntries = listOf(
            FlavorTextEntryDto(
                "A strange seed was\nplanted on its\nback at birth.The plant sprouts\nand grows with\nthis POKéMON.",
                UrlNameDto(
                    "en"
                ),
                UrlNameDto(
                    "red"
                ),
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
            height = 16,
            weight = 69,
            name = "bulbasaur",
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
            ),
            types = listOf(
                TypeDto(
                    slot = 1,
                    type = UrlNameDto("grass")
                ),
            ),
            baseExperience = 265,
            order = 1,
            locationAreaEncounters = "https://pokeapi.co/api/v2/pokemon/9/encounters",
            species = UrlNameDto(name = "bulbasaur"),
        )
    }

    fun getPokemonEntity(description: String = "") = PokemonEntity(
        id = 1,
        name = "bulbasaur",
        height = 16,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        description = description
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
    val typesEntity = TypeEntity(
        pokemonId = 1,
        description = "grass",
    )

    val pokemonRelation = PokemonRelation(
        pokemon = getPokemonEntity(),
        stats = listOf(statEntry),
        abilities = listOf(abilityEntity),
        types = listOf(typesEntity)
    )
}