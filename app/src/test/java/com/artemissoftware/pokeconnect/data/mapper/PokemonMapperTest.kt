package com.artemissoftware.pokeconnect.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.data.mappers.toAbilitiesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.mappers.toStat
import com.artemissoftware.pokeconnect.core.data.mappers.toStatsEntity
import com.artemissoftware.pokeconnect.data.TestMockData.abilityEntity
import com.artemissoftware.pokeconnect.data.TestMockData.getPokemonDtoWithArt
import com.artemissoftware.pokeconnect.data.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.data.TestMockData.pokedexEntryDto
import com.artemissoftware.pokeconnect.data.TestMockData.pokemon
import com.artemissoftware.pokeconnect.data.TestMockData.pokemonDto
import com.artemissoftware.pokeconnect.data.TestMockData.pokemonEntity
import com.artemissoftware.pokeconnect.data.TestMockData.pokemonRelation
import com.artemissoftware.pokeconnect.data.TestMockData.stat
import com.artemissoftware.pokeconnect.data.TestMockData.statEntry
import org.junit.jupiter.api.Test

class PokemonMapperTest {

    @Test
    fun `Map PokedexEntryDto to PokedexEntry`() {
        assertThat(pokedexEntryDto.toPokedexEntry()).isEqualTo(pokedexEntry)
    }

    @Test
    fun `Map PokemonDto to Pokemon`() {
        assertThat(pokemonDto.toPokemon()).isEqualTo(pokemon)
    }

    @Test
    fun `Map PokemonDto with no official artwork to image url that contains the front default sprite`() {

        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        val pokemon = getPokemonDtoWithArt(frontDefault = imageUrl)
            .toPokemon()

        assertThat(pokemon.imageUrl).isEqualTo(imageUrl)
    }

    @Test
    fun `Map PokemonDto with official artwork to image url that contains official artwork front Default sprite`() {

        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        val pokemon = getPokemonDtoWithArt(
                frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                officialArtworkFrontDefault = imageUrl,
            ).toPokemon()

        assertThat(pokemon.imageUrl).isEqualTo(imageUrl)
    }

    @Test
    fun `Map PokemonDto with official artwork to image url that contains official artwork front shiny sprite`() {

        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        val pokemon = getPokemonDtoWithArt(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            officialArtworkFrontShiny = imageUrl,
        ).toPokemon()

        assertThat(pokemon.imageUrl).isEqualTo(imageUrl)
    }

    @Test
    fun `Map Pokemon to a list of StatEntity`() {
        assertThat(pokemon.toStatsEntity()).isEqualTo(listOf(statEntry))
    }

    @Test
    fun `Map Pokemon to a list of AbilityEntity`() {
        assertThat(pokemon.toAbilitiesEntity()).isEqualTo(listOf(abilityEntity))
    }

    @Test
    fun `Map StatEntity to Stat`() {
        assertThat(statEntry.toStat()).isEqualTo(stat)
    }

    @Test
    fun `Map PokemonRelation to Pokemon`() {
        assertThat(pokemonRelation.toPokemon()).isEqualTo(pokemon)
    }

    @Test
    fun `Map Pokemon to PokemonEntity`() {
        assertThat(pokemon.toEntity()).isEqualTo(pokemonEntity)
    }
}