package com.artemissoftware.pokeconnect.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.TestMockData.abilityEntity
import com.artemissoftware.pokeconnect.TestMockData.getPokemonDtoWithArt
import com.artemissoftware.pokeconnect.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.TestMockData.pokedexEntryDto
import com.artemissoftware.pokeconnect.TestMockData.pokemonDto
import com.artemissoftware.pokeconnect.TestMockData.pokemonEntity
import com.artemissoftware.pokeconnect.TestMockData.pokemonFromApi
import com.artemissoftware.pokeconnect.TestMockData.pokemonFromDb
import com.artemissoftware.pokeconnect.TestMockData.pokemonRelation
import com.artemissoftware.pokeconnect.TestMockData.speciesDto
import com.artemissoftware.pokeconnect.TestMockData.statEntry
import com.artemissoftware.pokeconnect.TestMockData.typesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toAbilitiesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.mappers.toStatsEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toTypesEntity
import org.junit.jupiter.api.Test

class PokemonMapperTest {

    @Test
    fun `Map PokedexEntryDto to PokedexEntry`() {
        assertThat(pokedexEntryDto.toPokedexEntry()).isEqualTo(pokedexEntry)
    }

    @Test
    fun `Map PokemonDto to Pokemon`() {
        assertThat(pokemonDto.toPokemon()).isEqualTo(pokemonFromApi)
    }

    @Test
    fun `Map PokemonDto with SpeciesDto to Pokemon`() {
        assertThat(pokemonDto.toPokemon(speciesDto)).isEqualTo(pokemonFromApi)
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
        assertThat(pokemonFromApi.toStatsEntity()).isEqualTo(listOf(statEntry))
    }

    @Test
    fun `Map Pokemon to a list of AbilityEntity`() {
        assertThat(pokemonFromApi.toAbilitiesEntity()).isEqualTo(listOf(abilityEntity))
    }

    @Test
    fun `Map Pokemon to a list of TypeEntity`() {
        assertThat(pokemonFromApi.toTypesEntity()).isEqualTo(listOf(typesEntity))
    }

    @Test
    fun `Map PokemonRelation to Pokemon`() {
        assertThat(pokemonRelation.toPokemon()).isEqualTo(pokemonFromDb)
    }

    @Test
    fun `Map Pokemon to PokemonEntity`() {
        assertThat(pokemonFromApi.toEntity()).isEqualTo(pokemonEntity)
    }

    @Test
    fun `Map PokemonEntity to PokedexEntry`() {
        assertThat(pokemonEntity.toPokedexEntry()).isEqualTo(pokedexEntry)
    }
}