package com.uvg.app.domain.network

import com.uvg.app.data.network.dto.CharacterListDto
import com.uvg.app.data.network.dto.LocationListDto
import com.uvg.app.util.NetworkError
import com.uvg.app.util.Result

interface RickMortyApi {
    suspend fun getCharacters(): Result<CharacterListDto, NetworkError>
    suspend fun getLocations(): Result<LocationListDto, NetworkError>
}