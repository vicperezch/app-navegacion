package com.uvg.app.ui.main.character.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterProfileScreen(
    characterId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = CharacterProfileDestination(characterId = characterId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.characterProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable<CharacterProfileDestination> {
        CharacterProfileRoute(
            onNavigateBack = onNavigateBack,
            modifier = Modifier.fillMaxSize()
        )
    }
}