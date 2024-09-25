package com.uvg.app.navigation.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.app.navigation.main.MainDestination
import com.uvg.app.navigation.main.mainScreen
import com.uvg.app.navigation.main.navigateToMainScreen
import com.uvg.app.ui.login.LoginDestination
import com.uvg.app.ui.login.loginScreen

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToMainScreen()
            }
        )

        mainScreen(
            onLogOutClick = {
                navController.navigateUp()
                navController.clearBackStack<MainDestination>()
            }
        )
    }
}