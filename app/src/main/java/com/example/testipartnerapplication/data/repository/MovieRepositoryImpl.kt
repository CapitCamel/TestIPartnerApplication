package com.example.testipartnerapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.testipartnerapplication.data.SearchPagingSource
import com.example.testipartnerapplication.data.network.ApiService
import com.example.testipartnerapplication.data.network.DrugResponseItem
import com.example.testipartnerapplication.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor (
    private val apiService: ApiService
        ) : SearchRepository {

    override fun search(query: String?)=
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).liveData

    override suspend fun getDataDrug(id: Int): DrugResponseItem = apiService.getDataDrug(id)

}