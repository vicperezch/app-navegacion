package com.uvg.app.ui.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.app.ui.character.list.CharacterListDestination
import com.uvg.app.ui.character.list.characterListScreen
import com.uvg.app.ui.character.profile.characterProfileScreen
import com.uvg.app.ui.character.profile.navigateToCharacterProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNavGraph

fun NavController.navigateToCharacterGraph(navOptions: NavOptions? = null) {
    this.navigate(CharacterNavGraph, navOptions)
}

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharacterListDestination
    ) {
        characterListScreen(
            onCharacterClick = navController::navigateToCharacterProfileScreen
        )

        characterProfileScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}