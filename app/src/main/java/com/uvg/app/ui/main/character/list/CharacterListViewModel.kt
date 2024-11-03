package com.uvg.app.ui.main.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.local.CharacterDb
import com.uvg.app.data.repository.LocalCharacterRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.domain.CharacterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterRepository: CharacterRepository): ViewModel() {
    private var getDataJob: Job? = null
    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            CharacterListEvent.LoadingClick -> {
                getDataJob?.cancel()

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }

            CharacterListEvent.RetryClick -> {
                getData()
            }
        }
    }

    private fun getData() {
        getDataJob = viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            val characters = characterRepository.getCharacters()

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = characters
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)

                CharacterListViewModel(
                    characterRepository = LocalCharacterRepository(db.characterDao())
                )
            }
        }
    }
}