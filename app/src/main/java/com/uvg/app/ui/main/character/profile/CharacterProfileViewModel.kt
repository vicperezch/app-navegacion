package com.uvg.app.ui.main.character.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.uvg.app.data.local.CharacterDb
import com.uvg.app.data.repository.LocalCharacterRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.domain.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository
): ViewModel() {
    private val characterProfile = savedStateHandle.toRoute<CharacterProfileDestination>()
    private val _uiState = MutableStateFlow(CharacterProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            val character = characterRepository.getCharacterById(characterProfile.characterId)

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = character
                )
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
                val savedStateHandle = createSavedStateHandle()
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = Dependencies.provideDatabase(context)

                CharacterProfileViewModel(
                    characterRepository = LocalCharacterRepository(
                        characterDao = appDatabase.characterDao()
                    ),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}