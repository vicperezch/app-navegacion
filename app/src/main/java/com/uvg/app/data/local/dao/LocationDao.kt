package com.uvg.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uvg.app.data.local.entity.LocationEntity
import com.uvg.app.domain.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query("SELECT * FROM LocationEntity")
    suspend fun getAllLocations(): List<LocationEntity>

    @Query("SELECT * FROM LocationEntity WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationEntity
}