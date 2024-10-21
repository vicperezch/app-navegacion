package com.uvg.app.ui.main.location.details

import com.uvg.app.data.local.LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationDetailsViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val locationDetails = savedStateHandle.toRoute<LocationDetailsDestination>()
    private val _uiState = MutableStateFlow(LocationDetailsState(
        data = LocationDb.getLocationById(locationDetails.locationId)
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