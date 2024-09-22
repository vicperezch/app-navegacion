package com.uvg.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MainDestination

fun NavController.navigateToMainScreen(
    destination: MainDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.mainScreen() {
    composable<MainDestination> {
        MainRoute()
    }
}