package com.uvg.app.ui.main.location.list

sealed interface LocationListEvent {
    data object LoadingClick: LocationListEvent
    data object RetryClick: LocationListEvent
}