package com.uvg.app.data.repository

import com.uvg.app.data.local.CharacterDb
import com.uvg.app.data.local.dao.CharacterDao
import com.uvg.app.data.local.entity.mapToEntity
import com.uvg.app.data.local.entity.mapToModel
import com.uvg.app.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalCharacterRepository(private val characterDao: CharacterDao) {
    suspend fun getAllCharacters(): List<Character> {
        val localCharacters = characterDao.getAllCharacters()
        return localCharacters.map { characterEntity ->
            characterEntity.mapToModel()
        }
    }

    fun getCharacterByID(id: Int): Character {
        return characterDao.getCharacter(id)
    }

    suspend fun insertAllCharacters() {
        val characters = characterDao.getAllCharacters()

        if (characters.isEmpty()) {
            val remoteCharacters = CharacterDb.getAllCharacters()
            val localCharacters = remoteCharacters.map { character ->
                character.mapToEntity()
            }
            characterDao.insertAll(localCharacters)
        }
    }
}