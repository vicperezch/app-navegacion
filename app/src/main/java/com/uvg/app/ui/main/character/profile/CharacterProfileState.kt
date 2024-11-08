package com.uvg.app.ui.main.character.profile

import com.uvg.app.domain.Character

data class CharacterProfileState(
    val data: Character? = null,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)