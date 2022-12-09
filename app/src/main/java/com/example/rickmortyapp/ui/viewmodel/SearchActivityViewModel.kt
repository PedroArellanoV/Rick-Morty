package com.example.rickmortyapp.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.GetCharactersByNameUseCase
import com.example.rickmortyapp.domain.model.UiCharacterModel
import com.example.rickmortyapp.domain.model.UiRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val getAllCharactersByName: GetCharactersByNameUseCase): ViewModel() {

    val charactersRequest = MutableLiveData<UiRequestModel>()

    fun getCharactersByName(name: String){
        viewModelScope.launch {
            val result = getAllCharactersByName(name)
                charactersRequest.postValue(result)
        }
    }
}