package com.uvg.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.app.ui.login.LoginDestination
import com.uvg.app.ui.login.loginScreen
import com.uvg.app.ui.main.MainDestination
import com.uvg.app.ui.main.mainScreen
import com.uvg.app.ui.main.navigateToMainScreen
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
            }
        }
    }
}
