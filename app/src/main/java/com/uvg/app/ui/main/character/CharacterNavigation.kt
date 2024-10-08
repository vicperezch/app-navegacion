package com.uvg.app.ui.main.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.app.ui.main.character.list.CharacterListDestination
import com.uvg.app.ui.main.character.list.characterListScreen
import com.uvg.app.ui.main.character.profile.characterProfileScreen
import com.uvg.app.ui.main.character.profile.navigateToCharacterProfileScreen
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