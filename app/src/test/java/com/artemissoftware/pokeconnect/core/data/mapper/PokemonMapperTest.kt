package com.artemissoftware.pokeconnect.core.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.testdata.TestMockData.abilityEntity
import com.artemissoftware.pokeconnect.testdata.TestMockData.getPokemon
import com.artemissoftware.pokeconnect.testdata.TestMockData.getPokemonDtoWithArt
import com.artemissoftware.pokeconnect.testdata.TestMockData.getPokemonEntity
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokemonDto
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokemonRelation
import com.artemissoftware.pokeconnect.testdata.TestMockData.speciesDto
import com.artemissoftware.pokeconnect.testdata.TestMockData.statEntry
import com.artemissoftware.pokeconnect.testdata.TestMockData.typesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toAbilitiesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.mappers.toStatsEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toTypesEntity
import org.junit.jupiter.api.Test

class PokemonMapperTest {


    @Test
    fun `Map PokemonDto to Pokemon`() {
        assertThat(pokemonDto.toPokemon()).isEqualTo(getPokemon())
    }

    @Test
    fun `Map PokemonDto with SpeciesDto to Pokemon`() {
        assertThat(pokemonDto.toPokemon(speciesDto)).isEqualTo(getPokemon("A strange seed was planted on its back at birth.The plant sprouts and grows with this POKéMON."))
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
        assertThat(getPokemon().toStatsEntity()).isEqualTo(listOf(statEntry))
    }

    @Test
    fun `Map Pokemon to a list of AbilityEntity`() {
        assertThat(getPokemon().toAbilitiesEntity()).isEqualTo(listOf(abilityEntity))
    }

    @Test
    fun `Map Pokemon to a list of TypeEntity`() {
        assertThat(getPokemon().toTypesEntity()).isEqualTo(listOf(typesEntity))
    }

    @Test
    fun `Map PokemonRelation to Pokemon`() {
        assertThat(pokemonRelation.toPokemon()).isEqualTo(getPokemon(isFavorite = true))
    }

    @Test
    fun `Map Pokemon to PokemonEntity`() {
        assertThat(getPokemon().toEntity()).isEqualTo(getPokemonEntity())
    }

    @Test
    fun `Map PokemonEntity to PokedexEntry`() {
        assertThat(getPokemonEntity().toPokedexEntry()).isEqualTo(pokedexEntry)
    }
}