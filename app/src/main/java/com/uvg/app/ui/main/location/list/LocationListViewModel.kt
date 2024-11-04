package com.uvg.app.ui.main.location.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.network.dto.mapToCharacterModel
import com.uvg.app.data.network.dto.mapToLocationModel
import com.uvg.app.data.repository.LocalLocationRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.di.KtorDependencies
import com.uvg.app.domain.LocationRepository
import com.uvg.app.domain.network.RickMortyApi
import com.uvg.app.util.map
import com.uvg.app.util.onError
import com.uvg.app.util.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val locationRepository: LocationRepository,
    private val rickMortyApi: RickMortyApi
): ViewModel() {
    private var getDataJob: Job? = null
    private val _uiState = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun onEvent(event: LocationListEvent) {
        when (event) {
            LocationListEvent.LoadingClick -> {
                getDataJob?.cancel()

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }

            LocationListEvent.RetryClick -> {
                getData()
            }
        }
    }

    private fun getData() {
        getDataJob = viewModelScope.launch {
            rickMortyApi
                .getLocations()
                .map { response -> response.results.map { it.mapToLocationModel() }}
                .onSuccess { locations ->
                    locationRepository.initialSync(locations)

                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            hasError = false,
                            data = locations
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
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                val api = com.uvg.app.data.network.RickMortyApi(KtorDependencies.provideHttpClient())

                LocationListViewModel(
                    locationRepository = LocalLocationRepository(db.locationDao()),
                    rickMortyApi = api
                )
            }
        }
    }
}