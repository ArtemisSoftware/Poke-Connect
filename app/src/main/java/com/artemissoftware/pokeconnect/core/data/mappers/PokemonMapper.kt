package com.artemissoftware.pokeconnect.core.data.mappers

import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.database.entities.AbilityEntity
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.database.entities.StatEntity
import com.artemissoftware.pokeconnect.core.database.entities.TypeEntity
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.models.pokemon.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.pokemon.Pokemon
import com.artemissoftware.pokeconnect.core.models.pokemon.PokemonType
import com.artemissoftware.pokeconnect.core.models.pokemon.Stat
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.OfficialArtworkDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.PokemonDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.StatDto
import com.artemissoftware.pokeconnect.core.network.dto.species.SpeciesDto
import java.util.Locale


internal fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        stats = stats.map { it.toStat() },
        abilities = abilities.map { it.ability.name },
        imageUrl = sprites.other.officialArtwork.toUrl(default = sprites.frontDefault),
        types = types.map { PokemonType.getType(it.type.name) },
    )
}

internal fun PokemonDto.toPokemon(speciesDto: SpeciesDto): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        stats = stats.map { it.toStat() },
        abilities = abilities.map { it.ability.name },
        imageUrl = sprites.other.officialArtwork.toUrl(default = sprites.frontDefault),
        types = types.map { PokemonType.getType(it.type.name) },
        description = speciesDto.toDescription()
    )
}

internal fun PokemonRelation.toPokemon(): Pokemon {
    return Pokemon(
        id = pokemon.id,
        name = pokemon.name,
        height = pokemon.height,
        weight = pokemon.weight,
        imageUrl = pokemon.imageUrl,
        isFavorite = true,
        abilities = abilities.map { it.description },
        stats = stats.map { it.toStat() },
        types = types.map { PokemonType.getType(it.description) },
        description = pokemon.description
    )
}

internal fun Pokemon.toEntity(): PokemonEntity{
    return PokemonEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        description = description,
    )
}

internal fun Pokemon.toAbilitiesEntity(): List<AbilityEntity>{
    return abilities.map {
        AbilityEntity(
            pokemonId = id,
            description = it,
        )
    }
}

private fun StatEntity.toStat(): Stat {
    return Stat(
        abbreviation = description.toAbbreviation(),
        description = description,
        value = value,
    )
}

private fun StatDto.toStat(): Stat {
    return Stat(
        abbreviation = stat.name.toAbbreviation(),
        value = baseStat,
        description = stat.name
    )
}

private fun SpeciesDto.toDescription(): String{
    return (this.flavorTextEntries.firstOrNull() { it.language.name == "en" }?.flavorText ?: "").replace("\n", " ")
}

private fun String.toAbbreviation(): String {
    return when(this.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> abbreviatePhrase(this).upperCaseFirstChar()
    }
}

private fun abbreviatePhrase(phrase: String): String {
    val words = phrase.trim().split("\\s+".toRegex())

    if (words.size == 1) {
        val word = words[0]
        return if (word.length > 3) word.substring(0, 3).uppercase() else word.uppercase()
    }

    return words.joinToString("") { it.first().uppercase() }
}

internal fun Pokemon.toStatsEntity(): List<StatEntity>{
    return stats.map {
        StatEntity(
            pokemonId = id,
            description = it.description,
            value = it.value,
        )
    }
}

internal fun Pokemon.toTypesEntity(): List<TypeEntity>{
    return types.map {
        TypeEntity(
            pokemonId = id,
            description = it.description,
        )
    }
}

internal fun PokemonEntity.toPokedexEntry(): PokedexEntry {
    return PokedexEntry(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

private fun OfficialArtworkDto.toUrl(default: String): String{
    return when{
        frontDefault != null -> frontDefault
        frontShiny != null -> frontShiny
        else -> default
    }
}

