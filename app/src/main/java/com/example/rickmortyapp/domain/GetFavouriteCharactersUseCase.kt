package com.example.rickmortyapp.domain

import com.example.rickmortyapp.data.CharactersRepository
import com.example.rickmortyapp.data.local.entities.CharacterEntity
import javax.inject.Inject

class GetFavouriteCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke() = repository.getFavouritesCharacters()
}