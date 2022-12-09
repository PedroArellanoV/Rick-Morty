package com.example.rickmortyapp.data.remote

import com.example.rickmortyapp.data.model.RequestModelCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val retrofit: ApiService) {

    suspend fun getCharactersByPage(page: Int):RequestModelCharacter{
        return withContext(Dispatchers.IO){
            val response = retrofit.getCharactersByPage(page)
            response.body()!!
        }
    }

    suspend fun getCharactersByName(name: String):RequestModelCharacter{
        return withContext(Dispatchers.IO){
            val response = retrofit.getCharacterByName(name)
            response.body()!!
        }
    }
}