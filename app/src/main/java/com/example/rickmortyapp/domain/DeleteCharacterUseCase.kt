package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import com.example.rickmortyapp.domain.model.UiCharacterModel
import javax.inject.Inject

class DeleteCharacterUseCase @Inject constructor(private val repository: CharactersRepository){
    suspend operator fun invoke(characterToDelete: UiCharacterModel) = repository.deleteCharacter(characterToDelete)
}