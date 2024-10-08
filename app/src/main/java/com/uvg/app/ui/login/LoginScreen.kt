package com.uvg.app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.app.ui.theme.AppTheme
import com.uvg.app.R

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
) {
    LoginScreen(
        modifier = modifier,
        onLoginClick = onLoginClick
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
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

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLoginClick
            ) {
                Text(text = "Entrar")
            }
        }

        Text(text = "Victor Pérez - 23731")
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
                onLoginClick = {}
            )
        }
    }
}