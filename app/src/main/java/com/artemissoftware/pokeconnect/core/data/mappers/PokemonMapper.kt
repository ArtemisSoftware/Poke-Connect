package com.artemissoftware.pokeconnect.core.data.mappers

import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.models.Stat
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OfficialArtworkDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.StatDto

fun PokedexEntryDto.toPokedexEntry(): PokedexEntry{
    val id = url.toPokemonId()
    return PokedexEntry(
        id = id.toInt(),
        name = name,
        imageUrl = id.getImageUrl()
    )
}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        stats = stats.map { it.toStat() },
        abilities = abilities.map { it.ability.name },
        imageUrl = sprites.other.officialArtwork.toUrl(default = sprites.frontDefault)
    )
}

fun PokemonRelation.toPokemon(): Pokemon{
    return Pokemon(
        id = pokemon.id,
        name = pokemon.name,
        height = pokemon.height,
        weight = pokemon.weight,
        imageUrl = pokemon.imageUrl,
        abilities = abilities.map { it.description },
        stats = stats.map { it.toStat() }
    )
}

fun Pokemon.toEntity(): PokemonEntity{
    return PokemonEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        description = "", // TODO: mudar isto
    )
}

fun Pokemon.toAbilitiesEntity(): List<AbilityEntity>{
    return abilities.map {
        AbilityEntity(
            pokemonId = id,
            description = it,
        )
    }
}

fun StatEntity.toStat(): Stat{
    return Stat(
        description = description,
        value = value,
    )
}

fun Pokemon.toStatsEntity(): List<StatEntity>{
    return stats.map {
        StatEntity(
            pokemonId = id,
            description = it.description,
            value = it.value,
        )
    }
}

private fun OfficialArtworkDto.toUrl(default: String): String{
    return when{
        frontDefault != null -> frontDefault
        frontShiny != null -> frontShiny
        else -> default
    }
}

private fun StatDto.toStat(): Stat{
    return Stat(
        value = baseStat,
        description = stat.name
    )
}

private fun String.toPokemonId(): String {
    return if(this.endsWith("/")) {
        this.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        this.takeLastWhile { it.isDigit() }
    }
}

private fun String.getImageUrl(): String {
    return PokeApi.DEFAULT_IMAGE_URL + this + PokeApi.DEFAULT_IMAGE_URL_EXTENSION
}