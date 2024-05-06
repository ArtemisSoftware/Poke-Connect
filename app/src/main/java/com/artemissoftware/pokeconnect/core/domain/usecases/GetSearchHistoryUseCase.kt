package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.data.repository.SearchHistoryRepository
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(private val searchHistoryRepository: SearchHistoryRepository) {

    operator fun invoke() = searchHistoryRepository.getHistory()
}