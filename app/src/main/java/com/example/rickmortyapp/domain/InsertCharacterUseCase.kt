package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import com.example.rickmortyapp.data.local.CharactersDatabase
import com.example.rickmortyapp.data.local.entities.toDatabase
import com.example.rickmortyapp.domain.model.UiCharacterModel
import javax.inject.Inject

class InsertCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(characterToInsert: UiCharacterModel) {
        val character = repository.getCharacterById(characterToInsert.id)

        if (character == null) {
            repository.insertCharacter(characterToInsert)
        }
    }
}