package com.uvg.app.ui.login

data class LoginState(
    val username: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false
)