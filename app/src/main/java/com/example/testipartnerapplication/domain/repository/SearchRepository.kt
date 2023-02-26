package com.example.testipartnerapplication.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testipartnerapplication.data.network.DrugResponseItem

interface SearchRepository {
    fun search(
        search:String?
    ): LiveData<PagingData<DrugResponseItem>>

    suspend fun getDataDrug(id: Int): DrugResponseItem
}