package com.example.testipartnerapplication.presentation

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.testipartnerapplication.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel()  {
    private var currentQuery = MutableLiveData<String>()

    val data = currentQuery.switchMap { queryString ->
        searchUseCase.invoke(queryString).cachedIn(viewModelScope)
    }

    fun search(query: String) {
        currentQuery.value = query
    }
}