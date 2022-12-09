package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(name: String) = repository.getCharactersByName(name)
}