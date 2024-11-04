package com.uvg.app.data.network

import com.uvg.app.data.network.dto.CharacterListDto
import com.uvg.app.data.network.dto.LocationListDto
import com.uvg.app.domain.network.RickMortyApi
import com.uvg.app.util.NetworkError
import com.uvg.app.util.Result
import com.uvg.app.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RickMortyApi(private val httpClient: HttpClient): RickMortyApi {
    override suspend fun getCharacters(): Result<CharacterListDto, NetworkError> {
        return safeCall<CharacterListDto> {
            httpClient.get(
                "https://rickandmortyapi.com/api/character"
            )
        }
    }

    override suspend fun getLocations(): Result<LocationListDto, NetworkError> {
        return safeCall<LocationListDto> {
            httpClient.get(
                "https://rickandmortyapi.com/api/location"
            )
        }
    }
}