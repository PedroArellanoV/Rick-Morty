package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import com.example.rickmortyapp.data.model.RequestModelCharacter
import com.example.rickmortyapp.domain.model.UiRequestModel
import javax.inject.Inject

class GetCharactersByPageUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(page: Int) = repository.getCharactersByPage(page)
}