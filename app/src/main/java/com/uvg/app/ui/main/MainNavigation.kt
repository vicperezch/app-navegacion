package com.uvg.app.ui.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MainDestination

fun NavController.navigateToMainScreen(navOptions: NavOptions? = null) {
    this.navigate(MainDestination, navOptions)
}

fun NavGraphBuilder.mainScreen(
    onLogOutClick: () -> Unit
) {
    composable<MainDestination>() {
        MainScreen(onLogoutClick = onLogOutClick)
    }
}