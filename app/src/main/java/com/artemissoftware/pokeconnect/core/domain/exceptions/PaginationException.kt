package com.artemissoftware.pokeconnect.core.domain.exceptions

import com.artemissoftware.pokeconnect.core.domain.error.Error

class PaginationException(
    val error: Error,
) : RuntimeException()
