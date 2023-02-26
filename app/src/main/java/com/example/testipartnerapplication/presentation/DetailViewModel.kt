package com.example.testipartnerapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testipartnerapplication.data.network.DrugResponseItem
import com.example.testipartnerapplication.domain.usecase.DrugUseCase
import com.example.testipartnerapplication.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DrugUseCase
): ViewModel()   {
    private val _drug = MutableLiveData<DrugResponseItem>()
    val drug: LiveData<DrugResponseItem>
        get() = _drug

    fun getDrug(id: Int){
        viewModelScope.launch {
            _drug.value = useCase.invoke(id)
        }
    }

}