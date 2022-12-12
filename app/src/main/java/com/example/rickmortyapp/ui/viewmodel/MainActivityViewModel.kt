package com.example.rickmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.*
import com.example.rickmortyapp.domain.model.UiCharacterModel
import com.example.rickmortyapp.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val getAllCharactersByPage: GetCharactersByPageUseCase,
    private val getFavouriteCharactersUseCase: GetFavouriteCharactersUseCase,
    private val insertCharacterUseCase: InsertCharacterUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
    private val getCharactersById: GetCharacterByIdUseCase

) : ViewModel() {

    val charactersList = MutableLiveData<List<UiCharacterModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val favouriteCharacters = MutableLiveData<List<UiCharacterModel>>()

    fun getCharactersByPage(page: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getAllCharactersByPage(page)
            val characters = result.results.map { it.toDomain() }
            characters.map {
                it.isFavourite = getCharactersById(it.id) != null
            }
            charactersList.postValue(characters)
            isLoading.postValue(false)
        }
    }

    fun getFavouriteCharacters() {
        viewModelScope.launch {
            val result = getFavouriteCharactersUseCase()
            favouriteCharacters.postValue(result)
        }
    }

    fun insertCharacter(characterToInsert: UiCharacterModel) {
        viewModelScope.launch {
            characterToInsert.isFavourite = true
            insertCharacterUseCase(characterToInsert)
        }
    }

    fun deleteCharacter(characterToDelete: UiCharacterModel) {
        viewModelScope.launch {
            deleteCharacterUseCase(characterToDelete)
        }
    }


}