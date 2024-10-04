package com.uvg.app.ui.main.userProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.app.R
import com.uvg.app.ui.theme.AppTheme

@Composable
fun UserProfileRoute(
    modifier: Modifier = Modifier,
    onLogOutClick: () -> Unit
) {
    UserProfileScreen(
        modifier = modifier,
        onLogOutClick = onLogOutClick
    )
}

@Composable
private fun UserProfileScreen(
    modifier: Modifier = Modifier,
    onLogOutClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.profile_im),
            contentDescription = "Profile image",
            modifier = Modifier
                .width(250.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nombre:")
                Text(text = "Victor Pérez")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Carné:")
                Text(text = "23731")
            }
        }

        OutlinedButton(onClick = onLogOutClick) {
            Text(text = "Cerrar sesión")
        }
    }
}

@Composable
@Preview
private fun PreviewLoginScreen() {
    AppTheme {
        Surface {
            UserProfileScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                onLogOutClick = {}
            )
        }
    }
}