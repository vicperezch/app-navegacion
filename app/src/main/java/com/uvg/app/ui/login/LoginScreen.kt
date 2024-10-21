package com.uvg.app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.app.ui.theme.AppTheme
import com.uvg.app.R

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {
   val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        modifier = modifier,
        onLoginClick = {
            if (state.username.isNotBlank()) {
                viewModel.saveUsername()
                onLoginClick()
            }
        },
        username = state.username,
        loading = state.loading,
        onUsernameChange = {
            viewModel.onUsernameChange(it)
        }
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    username: String,
    loading: Boolean,
    onLoginClick: () -> Unit,
    onUsernameChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_rm),
                contentDescription = "Rick and Morty logo",
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                placeholder = {
                    Text(text = "Nombre")
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLoginClick,
                enabled = !loading
            ) {
                Text(text = "Entrar")
            }
        }

        Text(text = "Victor Pérez - 23731")

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
@Preview
private fun PreviewLoginScreen() {
    AppTheme {
        Surface {
            LoginScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                onLoginClick = {},
                username = "",
                onUsernameChange = {},
                loading = false
            )
        }
    }
}