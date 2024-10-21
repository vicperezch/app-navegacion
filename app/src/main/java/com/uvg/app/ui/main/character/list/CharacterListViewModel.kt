package com.uvg.app.ui.main.character.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.local.CharacterDb
import com.uvg.app.data.local.dao.CharacterDao
import com.uvg.app.data.repository.LocalCharacterRepository
import com.uvg.app.di.Dependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterDao: CharacterDao): ViewModel() {
    private val localCharacterRepository = LocalCharacterRepository(characterDao)
    private val _uiState = MutableStateFlow(
        CharacterListState(
            data = CharacterDb.getAllCharacters())
    )
    val uiState = _uiState.asStateFlow()

    fun onGetData() {
        viewModelScope.launch {
            localCharacterRepository.insertAllCharacters()

            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false,
                    data = localCharacterRepository.getAllCharacters()
                )
            }

            delay(4000)

            _uiState.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    fun onLoadingClick() {
        _uiState.update { it.copy(
            isLoading = false,
            hasError = true
        ) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                CharacterListViewModel(
                    characterDao = db.characterDao()
                )
            }
        }
    }
}