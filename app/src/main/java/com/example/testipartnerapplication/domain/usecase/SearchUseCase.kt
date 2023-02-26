package com.example.testipartnerapplication.domain.usecase

import com.example.testipartnerapplication.data.network.DrugResponseItem
import com.example.testipartnerapplication.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(search:String?) = repository.search(search = search)
}