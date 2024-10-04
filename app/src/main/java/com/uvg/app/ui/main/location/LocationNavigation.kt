package com.uvg.app.ui.main.location

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.uvg.app.ui.main.location.details.locationDetailsScreen
import com.uvg.app.ui.main.location.details.navigateToLocationDetailsScreen
import com.uvg.app.ui.main.location.list.LocationListDestination
import com.uvg.app.ui.main.location.list.locationListScreen
import com.uvg.app.ui.main.location.list.navigateToLocationListScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocationNavGraph

fun NavController.navigateToLocationGraph(
    navOptions: NavOptions? = null
) {
    this.navigate(LocationNavGraph, navOptions)
}

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNavGraph>(
        startDestination = LocationListDestination
    ) {
        locationListScreen(
            onLocationClick = navController::navigateToLocationDetailsScreen
        )

        locationDetailsScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}