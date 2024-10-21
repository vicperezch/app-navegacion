package com.uvg.app.ui.main.location.list

import com.uvg.app.data.local.LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.data.local.dao.CharacterDao
import com.uvg.app.data.local.dao.LocationDao
import com.uvg.app.data.repository.LocalLocationRepository
import com.uvg.app.di.Dependencies
import com.uvg.app.ui.main.character.list.CharacterListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(private val locationDao: LocationDao): ViewModel() {
    private val locationRepository = LocalLocationRepository(locationDao)
    private val _uiState = MutableStateFlow(
        LocationListState(
        data = LocationDb.getAllLocations())
    )
    val uiState = _uiState.asStateFlow()

    fun onGetData() {
        viewModelScope.launch {
            locationRepository.insertAllLocations()

            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false,
                    data = locationRepository.getAllLocations()
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
                LocationListViewModel(
                    locationDao = db.locationDao()
                )
            }
        }
    }
}