package com.uvg.app.ui.locationList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uvg.app.navigation.NDestination
import kotlinx.serialization.Serializable

@Serializable
data object LocationListDestination: NDestination

fun NavController.navigateToLocationListScreen(
    destination: LocationListDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationListScreen(
    onLocationClick: (Int) -> Unit
) {
    composable<LocationListDestination> {
        LocationListRoute(
            modifier = Modifier.fillMaxSize(),
            onLocationClick = onLocationClick
        )
    }
}