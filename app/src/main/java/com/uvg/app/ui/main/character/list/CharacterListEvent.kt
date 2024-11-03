package com.uvg.app.ui.main.character.list

sealed interface CharacterListEvent {
    data object LoadingClick: CharacterListEvent
    data object RetryClick: CharacterListEvent
}