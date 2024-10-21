package com.uvg.app.data.repository

import com.uvg.app.data.local.LocationDb
import com.uvg.app.data.local.dao.LocationDao
import com.uvg.app.data.local.entity.mapToEntity
import com.uvg.app.data.local.entity.mapToModel
import com.uvg.app.domain.Location
import kotlinx.coroutines.flow.map

class LocalLocationRepository(
    private val locationDao: LocationDao
) {
    suspend fun getAllLocations(): List<Location> {
        val localLocations = locationDao.getAllLocations()

        return localLocations.map { location ->
            location.mapToModel()
        }
    }

    fun getLocationByID(id: Int): Location {
        return locationDao.getLocation(id)
    }

    suspend fun insertAllLocations() {
        val locations = locationDao.getAllLocations()

        if (locations.isEmpty()) {
            val remoteLocations = LocationDb.getAllLocations()
            val localLocations = remoteLocations.map { localLocation ->
                localLocation.mapToEntity()
            }

            locationDao.insertAll(localLocations)
        }
    }
}