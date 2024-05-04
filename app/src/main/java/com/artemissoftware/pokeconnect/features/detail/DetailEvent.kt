package com.artemissoftware.pokeconnect.features.detail

sealed class DetailEvent{
    data class UpdateSelectedTab(val index: Int): DetailEvent()
    data object UpdateFavorite: DetailEvent()
}
