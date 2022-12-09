package com.example.rickmortyapp.data.remote

import com.example.rickmortyapp.data.model.RequestModelCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): Response<RequestModelCharacter>

    @GET("character")
    suspend fun getCharacterByName(@Query("name") name: String): Response<RequestModelCharacter>

    @GET("character")
    suspend fun getAllCharacters(): Response<RequestModelCharacter>
}