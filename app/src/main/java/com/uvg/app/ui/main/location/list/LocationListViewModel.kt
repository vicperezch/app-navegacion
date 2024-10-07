package com.uvg.app.ui.main.location.list

import LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState = MutableStateFlow(
        LocationListState(
        data = locationDb.getAllLocations())
    )
    val uiState = _uiState.asStateFlow()

    fun onGetData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
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
}