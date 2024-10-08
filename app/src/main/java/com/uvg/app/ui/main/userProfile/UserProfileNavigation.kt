package com.uvg.app.ui.main.userProfile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object UserProfileDestination

fun NavController.navigateToUserProfileScreen(
    destination: UserProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.userProfileScreen(
    onLogoutClick: () -> Unit
) {
    composable<UserProfileDestination> {
        UserProfileRoute(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            onLogOutClick = onLogoutClick
        )
    }
}