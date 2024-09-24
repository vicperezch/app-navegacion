package com.uvg.app.ui.location.list

import Location
import LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.app.ui.theme.AppTheme

@Composable
fun LocationListRoute(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit
) {
    LocationListScreen(
        modifier = modifier,
        locationDb = LocationDb(),
        onLocationClick = onLocationClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListScreen(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    locationDb: LocationDb
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(text = "Locations",)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(locationDb.getAllLocations()) { item ->
                LocationItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(item.id) },
                    location = item
                )
            }
        }
    }
}

@Composable
private fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location
) {
    Column(modifier = modifier) {
        Text(
            text = location.name
        )

        Text(
            text = location.type,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview
private fun PreviewCharacterProfile() {
    AppTheme {
        Surface {
            LocationListScreen(
                modifier = Modifier.fillMaxSize(),
                locationDb = LocationDb(),
                onLocationClick = {}
            )
        }
    }
}