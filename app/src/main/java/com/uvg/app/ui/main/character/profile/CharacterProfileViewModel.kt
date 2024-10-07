package com.uvg.app.ui.main.character.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.app.data.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val characterDb = CharacterDb()
    private val characterProfile = savedStateHandle.toRoute<CharacterProfileDestination>()
    private val _uiState = MutableStateFlow(
        CharacterProfileState(
        data = characterDb.getCharacterById(characterProfile.characterId)
    ))
    val uiState = _uiState.asStateFlow()

    fun onGetData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(2000)

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
}