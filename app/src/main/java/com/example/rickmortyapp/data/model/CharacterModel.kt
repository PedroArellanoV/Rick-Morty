package com.example.rickmortyapp.data.model


data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin?,
    val location: Location?,
    val image: String,
    val episode: ArrayList<String>?,
    val url: String,
    val created: String
)