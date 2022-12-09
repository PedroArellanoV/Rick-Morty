package com.example.rickmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortyapp.data.local.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    suspend fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM character_table ORDER BY name DESC ")
    suspend fun getInOrder(): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE id = :id")
    suspend fun getById(id: Int): CharacterEntity?

    @Insert
    suspend fun insertCharacter(character: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}