package com.uvg.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uvg.app.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onGetData: () -> Unit,
    errorText: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.error_ic),
            contentDescription = "Error",
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            tint = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error
        )

        Text(
            text = "Intenta de nuevo.",
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(onClick = onGetData) {
            Text(
                text = "Reintentar",
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }
    }
}