package com.artemissoftware.pokeconnect.core.presentation.util.extensions

import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.domain.error.DataError
import com.artemissoftware.pokeconnect.core.domain.error.Error
import com.artemissoftware.pokeconnect.core.domain.error.PokemonError
import com.artemissoftware.pokeconnect.core.ui.text.UiText


fun Error.toUiText(): UiText {
    return when (this) {
        is DataError.NetworkError -> {
            this.asUiText()
        }
        is PokemonError -> {
            this.asUiText()
        }
        else -> UiText.StringResource(R.string.error_not_mapped)
    }
}

private fun DataError.NetworkError.asUiText(): UiText {
    return when (this) {
        DataError.NetworkError.Connect -> UiText.StringResource(
            R.string.connection_error,
        )
        is DataError.NetworkError.Error -> UiText.DynamicString(this.message)
        DataError.NetworkError.SocketTimeout -> UiText.StringResource(
            R.string.connection_socket_time_out,
        )
        DataError.NetworkError.Unknown -> UiText.StringResource(
            R.string.unknown_error,
        )
        DataError.NetworkError.UnknownHost -> UiText.StringResource(
            R.string.unknown_host_error,
        )
    }
}

private fun PokemonError.asUiText(): UiText {
    return when (this) {
        PokemonError.SearchWithNoResults -> UiText.StringResource(
            R.string.search_no_results,
        )
    }
}