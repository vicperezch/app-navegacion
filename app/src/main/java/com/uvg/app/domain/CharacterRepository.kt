package com.uvg.app.domain

interface CharacterRepository {
    suspend fun initialSync(): Boolean
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}