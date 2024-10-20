package com.uvg.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.app.dataStore
import com.uvg.app.data.DataStoreUserPrefs
import com.uvg.app.domain.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (private val userPreferences: UserPreferences): ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val state = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { state ->
            state.copy(
                username = username
            )
        }
    }

    fun saveUsername() {
        viewModelScope.launch {
            userPreferences.setUsername(_uiState.value.username)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                LoginViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}