package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(id: Int) = repository.getCharacterById(id)
}