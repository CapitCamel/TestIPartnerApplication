package com.example.testipartnerapplication.domain.usecase

import com.example.testipartnerapplication.domain.repository.SearchRepository
import javax.inject.Inject

class DrugUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(id: Int) = repository.getDataDrug(id = id)
}