package com.uvg.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.app.domain.Location

@Entity
data class LocationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationEntity.mapToModel(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun Location.mapToEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}