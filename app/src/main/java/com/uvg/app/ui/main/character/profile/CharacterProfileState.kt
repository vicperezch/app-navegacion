package com.uvg.app.ui.main.character.profile

import com.uvg.app.data.Character

data class CharacterProfileState(
    val data: Character,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)