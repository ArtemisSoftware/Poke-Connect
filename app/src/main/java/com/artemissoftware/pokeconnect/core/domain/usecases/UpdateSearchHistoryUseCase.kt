package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.data.repository.SearchHistoryRepository
import com.artemissoftware.pokeconnect.core.models.search.SearchResult
import javax.inject.Inject

class UpdateSearchHistoryUseCase @Inject constructor(private val searchHistoryRepository: SearchHistoryRepository) {

    suspend operator fun invoke(searchResult: SearchResult) = searchHistoryRepository.addSearch(searchResult = searchResult)
}