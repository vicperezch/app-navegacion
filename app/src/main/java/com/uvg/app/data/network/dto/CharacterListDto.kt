package com.uvg.app.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDto(
    val results: List<CharacterDto>,
)