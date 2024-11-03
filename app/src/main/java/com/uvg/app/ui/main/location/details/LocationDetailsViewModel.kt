package com.uvg.app.ui.main.location.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.uvg.app.data.repository.LocalLocationRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.domain.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val locationRepository: LocationRepository
): ViewModel() {
    private val locationDetails = savedStateHandle.toRoute<LocationDetailsDestination>()
    private val _uiState = MutableStateFlow(LocationDetailsState())
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

            val location = locationRepository.getLocationById(locationDetails.locationId)

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = location
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

                LocationDetailsViewModel(
                    locationRepository = LocalLocationRepository(appDatabase.locationDao()),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}