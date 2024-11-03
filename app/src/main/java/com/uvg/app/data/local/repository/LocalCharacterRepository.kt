package com.uvg.app.data.repository

import com.uvg.app.data.local.CharacterDb
import com.uvg.app.data.local.dao.CharacterDao
import com.uvg.app.data.local.entity.mapToEntity
import com.uvg.app.data.local.entity.mapToModel
import com.uvg.app.domain.Character
import com.uvg.app.domain.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class LocalCharacterRepository(private val characterDao: CharacterDao): CharacterRepository {
    override suspend fun initialSync(): Boolean {
        return try {
            if (characterDao.getAllCharacters().isEmpty()) {
                val characterDb = CharacterDb()
                val charactersToInsert = characterDb.getAllCharacters().map { it.mapToEntity() }
                characterDao.insertAll(charactersToInsert)
            }

            return true

        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println(e)
            false
        }
    }

    override suspend fun getCharacters(): List<Character> {
        delay(2000L)
        return characterDao.getAllCharacters().map { it.mapToModel() }
    }

    override suspend fun getCharacterById(id: Int): Character {
        delay(2000L)
        return characterDao.getCharacterById(id).mapToModel()
    }
}
