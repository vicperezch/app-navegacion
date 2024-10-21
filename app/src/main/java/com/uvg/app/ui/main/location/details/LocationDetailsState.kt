package com.uvg.app.ui.main.location.details

import com.uvg.app.domain.Location

data class LocationDetailsState(
    val data: Location,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)