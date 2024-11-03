package com.uvg.app.data.repository

import com.uvg.app.data.local.LocationDb
import com.uvg.app.data.local.dao.LocationDao
import com.uvg.app.data.local.entity.mapToEntity
import com.uvg.app.data.local.entity.mapToModel
import com.uvg.app.domain.Location
import com.uvg.app.domain.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.map
import kotlin.coroutines.coroutineContext

class LocalLocationRepository(private val locationDao: LocationDao): LocationRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            if (locationDao.getAllLocations().isEmpty()) {
                val locationDb = LocationDb()
                val locationsToInsert = locationDb.getAllLocations().map { it.mapToEntity() }
                locationDao.insertAll(locationsToInsert)
            }
            true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println(e)
            false
        }
    }

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDao.getAllLocations().map { it.mapToModel() }
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDao.getLocationById(id).mapToModel()
    }
}