package com.uvg.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uvg.app.data.local.entity.CharacterEntity
import com.uvg.app.domain.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacter(id: Int): Character

    @Insert
    suspend fun insertAll(characters: List<CharacterEntity>)
}