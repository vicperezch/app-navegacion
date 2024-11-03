package com.uvg.app.ui.main.location.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.repository.LocalLocationRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.domain.LocationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(private val locationRepository: LocationRepository): ViewModel() {
    private var getDataJob: Job? = null
    private val _uiState = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

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
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            val locations = locationRepository.getLocations()

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = locations
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {

            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)

                LocationListViewModel(
                    locationRepository = LocalLocationRepository(db.locationDao())
                )
            }
        }
    }
}