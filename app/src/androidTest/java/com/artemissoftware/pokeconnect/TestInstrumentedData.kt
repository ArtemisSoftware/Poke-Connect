package com.artemissoftware.pokeconnect

import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.models.PokemonType
import com.artemissoftware.pokeconnect.core.models.Stat

object TestInstrumentedData {

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
            value = 45,
        ),
        Stat(
            abbreviation = "SpAtk",
            description = "special-attack",
            value = 100,
        )
    )

    val abilities = listOf("torrent")
    val types = listOf(PokemonType.GRASS, PokemonType.POISON)

    fun getPokemon(description: String = "", isFavorite: Boolean = false, currentAbilities: List<String> = abilities): Pokemon{
        return Pokemon(
            id = 1,
            name = "bulbasaur",
            height = 16,
            weight = 69,
            isFavorite = isFavorite,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            stats = listOf(stat),
            abilities = currentAbilities,
            types = types,
            description = description
        )
    }

    val pokedexEntry = PokedexEntry(
        id = 1,
        name = "bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    )

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
    val typesEntity = TypeEntity(
        pokemonId = 1,
        description = "grass",
    )

    val pokemonRelation = PokemonRelation(
        pokemon = pokemonEntity,
        stats = listOf(statEntry),
        abilities = listOf(abilityEntity),
        types = listOf(typesEntity),
    )

    val searchHistoryEntity = SearchHistoryEntity(
        description = "1",
        note = "bulbasaur"
    )
}