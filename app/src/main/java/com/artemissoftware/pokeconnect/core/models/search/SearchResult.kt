package com.artemissoftware.pokeconnect.core.models.search

data class SearchResult(
    val description: String,
    val note: String? = null,
){
    fun entry() = note?.let { "$description - $note" } ?: run { description }
}
