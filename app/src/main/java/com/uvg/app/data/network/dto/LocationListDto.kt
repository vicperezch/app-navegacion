package com.uvg.app.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListDto(
    val results: List<LocationDto>
)