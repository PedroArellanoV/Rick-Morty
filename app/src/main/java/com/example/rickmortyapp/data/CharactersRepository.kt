package com.example.rickmortyapp.data

import com.example.rickmortyapp.data.local.dao.CharacterDao
import com.example.rickmortyapp.data.local.entities.toDatabase
import com.example.rickmortyapp.data.remote.CharacterService
import com.example.rickmortyapp.domain.model.UiCharacterModel
import com.example.rickmortyapp.domain.model.UiRequestModel
import com.example.rickmortyapp.domain.model.toDomain
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val retrofit: CharacterService,
    private val characterDao: CharacterDao
) {
    suspend fun getCharactersByPage(page: Int): UiRequestModel {
        val response = retrofit.getCharactersByPage(page)
        return response.toDomain()

    }

    suspend fun getCharactersByName(name: String): UiRequestModel {
        val response = retrofit.getCharactersByName(name)
        return response.toDomain()
    }

    suspend fun getFavouritesCharacters(): List<UiCharacterModel> {
        val response = characterDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun insertCharacter(character: UiCharacterModel) {
        val characterInsert = character.toDatabase()
        characterDao.insertCharacter(characterInsert)
    }

    suspend fun getCharacterById(id: Int): UiCharacterModel? {
        val response = characterDao.getById(id)
        return response?.toDomain()
    }

    suspend fun deleteCharacter(character: UiCharacterModel){
        val characterToDelete = character.toDatabase()
        characterDao.deleteCharacter(characterToDelete)
    }
}