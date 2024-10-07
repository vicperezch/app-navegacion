package com.uvg.app.ui.main.character.list

import com.uvg.app.data.Character

data class CharacterListState(
    val data: List<Character>,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)