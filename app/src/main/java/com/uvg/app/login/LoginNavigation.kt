package com.uvg.app.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<LoginDestination> {
        LoginRoute(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            onLoginClick = onLoginClick
        )
    }
}