package com.uvg.app.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.app.navigation.AuthState
import com.uvg.app.navigation.AuthViewModel
import com.uvg.app.ui.login.LoginDestination
import com.uvg.app.ui.login.loginScreen
import com.uvg.app.ui.main.MainDestination
import com.uvg.app.ui.main.mainScreen
import com.uvg.app.ui.main.navigateToMainScreen
import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
) {
    val navController = rememberNavController()
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = SplashDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = authViewModel::login
        )

        mainScreen(
            onLogOutClick = authViewModel::logout
        )

        composable<SplashDestination> {}
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            AuthState.Authenticated -> {
                navController.navigate(MainDestination) {
                    popUpTo(LoginDestination) {
                        inclusive = true
                    }
                }
            }

            AuthState.NonAuthenticated -> {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }

            AuthState.Loading -> {}
        }
    }
}