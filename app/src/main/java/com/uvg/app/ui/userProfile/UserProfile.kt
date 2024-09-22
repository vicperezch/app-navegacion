package com.uvg.app.ui.userProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.app.ui.theme.AppTheme

@Composable
private fun UserProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        
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
            )
        }
    }
}