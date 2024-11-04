package com.uvg.app.ui.main.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.local.entity.mapToEntity
import com.uvg.app.data.local.repository.LocalCharacterRepository
import com.uvg.app.data.network.dto.mapToCharacterModel
import com.uvg.app.di.Dependencies
import com.uvg.app.di.KtorDependencies
import com.uvg.app.domain.CharacterRepository
import com.uvg.app.domain.network.RickMortyApi
import com.uvg.app.util.map
import com.uvg.app.util.onError
import com.uvg.app.util.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val rickMortyApi: RickMortyApi,
    private val characterRepository: CharacterRepository
): ViewModel() {
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
            rickMortyApi
                .getCharacters()
                .map { response -> response.results.map { it.mapToCharacterModel() }}
                .onSuccess { characters ->
                    characterRepository.initialSync(characters)
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            hasError = false,
                            data = characters
                        )
                    }
                }
                .onError {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val api = com.uvg.app.data.network.RickMortyApi(KtorDependencies.provideHttpClient())
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)

                CharacterListViewModel(
                    rickMortyApi = api,
                    characterRepository = LocalCharacterRepository(db.characterDao())
                )
            }
        }
    }
}