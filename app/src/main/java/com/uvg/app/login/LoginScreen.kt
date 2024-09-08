package com.uvg.app.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_rm),
                contentDescription = "Rick and Morty logo",
            )

            Button(
                modifier = Modifier.width(225.dp),
                onClick = onLoginClick
            ) {
                Text(text = "Entrar")
            }
        }

        Text(
            text = "Victor Pérez - 23731",
            modifier = Modifier
        )
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