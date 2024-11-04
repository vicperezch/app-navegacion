package com.uvg.app.domain

interface LocationRepository {
    suspend fun initialSync(locations: List<Location>): Boolean
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}