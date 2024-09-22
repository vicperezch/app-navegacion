package com.uvg.app.ui.characterProfile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uvg.app.navigation.NDestination
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterProfileScreen(
    destination: CharacterProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable<CharacterProfileDestination> { backstackEntry ->
        val destination: CharacterProfileDestination = backstackEntry.toRoute()
        CharacterProfileRoute(
            id = destination.characterId,
            onNavigateBack = onNavigateBack,
            modifier = Modifier.fillMaxSize()
        )
    }
}