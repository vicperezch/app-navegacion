package com.uvg.app.ui.main.character.list

import com.uvg.app.domain.Character

data class CharacterListState(
    val data: List<Character> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)