package com.uvg.app.domain

interface LocationRepository {
    suspend fun initialSync(): Boolean
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}