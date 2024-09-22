package com.uvg.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.app.navigation.MainDestination
import com.uvg.app.navigation.mainScreen
import com.uvg.app.navigation.navigateToMainScreen
import com.uvg.app.ui.characterList.CharacterListDestination
import com.uvg.app.ui.characterList.characterListScreen
import com.uvg.app.ui.characterList.navigateToCharacterListScreen
import com.uvg.app.ui.characterProfile.CharacterProfileDestination
import com.uvg.app.ui.characterProfile.characterProfileScreen
import com.uvg.app.ui.characterProfile.navigateToCharacterProfileScreen
import com.uvg.app.ui.login.LoginDestination
import com.uvg.app.ui.login.loginScreen
import com.uvg.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        loginScreen (
                            onLoginClick = {
                                navController.navigateToMainScreen(
                                    destination = MainDestination
                                )
                            }
                        )

                        mainScreen()
                    }
                }
            }
        }
    }
}
