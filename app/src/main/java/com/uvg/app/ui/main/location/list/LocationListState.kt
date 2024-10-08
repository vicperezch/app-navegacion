package com.uvg.app.ui.main.location.list

import Location

data class LocationListState(
    val data: List<Location>,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)