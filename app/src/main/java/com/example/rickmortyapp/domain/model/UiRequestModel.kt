package com.example.rickmortyapp.domain.model

import com.example.rickmortyapp.data.model.CharacterModel
import com.example.rickmortyapp.data.model.Info
import com.example.rickmortyapp.data.model.RequestModelCharacter

data class UiRequestModel(
    val info: Info,
    val results: List<CharacterModel>
)

fun RequestModelCharacter.toDomain() = UiRequestModel(info, results)