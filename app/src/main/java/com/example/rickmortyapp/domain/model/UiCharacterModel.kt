package com.example.rickmortyapp.domain.model

import com.example.rickmortyapp.data.local.entities.CharacterEntity
import com.example.rickmortyapp.data.model.CharacterModel

data class UiCharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String,
    var isFavourite: Boolean?
)

fun CharacterEntity.toDomain() = UiCharacterModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    url = url,
    created = created,
    isFavourite = null
)
fun CharacterModel.toDomain() = UiCharacterModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    url = url,
    created = created,
    isFavourite = null
)