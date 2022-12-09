package com.example.rickmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.data.local.CharactersDatabase
import com.example.rickmortyapp.domain.*
import com.example.rickmortyapp.domain.model.UiCharacterModel
import com.example.rickmortyapp.domain.model.UiRequestModel
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

    val charactersPage = MutableLiveData<UiRequestModel>()
    val isLoading = MutableLiveData<Boolean>()
    val favouriteCharacters = MutableLiveData<List<UiCharacterModel>>()
    val isFavourite = MutableLiveData<Boolean>()

    fun getCharactersByPage(page: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getAllCharactersByPage(page)
            charactersPage.postValue(result)
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
            insertCharacterUseCase(characterToInsert)
        }
    }

    fun deleteCharacter(characterToDelete: UiCharacterModel) {
        viewModelScope.launch {
            deleteCharacterUseCase(characterToDelete)
        }
    }

    fun getCharacterId(id: Int){
        viewModelScope.launch {
            if (getCharactersById(id) != null){
                isFavourite.postValue(true)
            }else{
                isFavourite.postValue(false)
            }
        }
    }


}