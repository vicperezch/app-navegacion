package com.uvg.app.ui.location.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDestination(
    val locationId: Int
)

fun NavController.navigateToLocationDetailsScreen(
    destination: LocationDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationDetailsScreen(
    onNavigateBack: () -> Unit
) {
    composable<LocationDetailsDestination> { backstackEntry ->
        val destination: LocationDetailsDestination = backstackEntry.toRoute()

        LocationDetailsRoute(
            modifier = Modifier.fillMaxSize(),
            onNavigateBack = onNavigateBack,
            locationId = destination.locationId
        )
    }
}