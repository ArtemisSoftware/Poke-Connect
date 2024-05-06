package com.artemissoftware.pokeconnect.core.models.pokemon

enum class PokemonType(val description: String) {

    NORMAL("normal"),
    FIRE("fire"),
    WATER("water"),
    ELECTRIC("electric"),
    GRASS("grass"),
    ICE("ice"),
    FIGHTING("fighting"),
    POISON("poison"),
    GROUND("ground"),
    FLYING("flying"),
    PSYCHIC("psychic"),
    BUG("bug"),
    ROCK("rock"),
    GHOST("ghost"),
    DRAGON("dragon"),
    DARK("dark"),
    STEEL("steel"),
    FAIRY("fairy"),
    UNKNOWN("Not typified");

    companion object{
        fun getType(description: String) = entries.find { it.description == description.lowercase() } ?: UNKNOWN
    }
}