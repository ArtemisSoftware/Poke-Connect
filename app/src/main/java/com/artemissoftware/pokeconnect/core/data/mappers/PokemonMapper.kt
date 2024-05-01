package com.artemissoftware.pokeconnect.core.data.mappers

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