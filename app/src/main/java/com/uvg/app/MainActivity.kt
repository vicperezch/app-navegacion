package com.uvg.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.uvg.app.navigation.AuthState
import com.uvg.app.navigation.AuthViewModel
import com.uvg.app.ui.AppContent
import com.uvg.app.ui.theme.AppTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels { AuthViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition {
            authViewModel.authStatus.value is AuthState.Loading
        }

        setContent {
            AppTheme {
                Surface {
                    AppContent()
                }
            }
        }
    }
}
