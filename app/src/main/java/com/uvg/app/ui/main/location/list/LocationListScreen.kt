package com.uvg.app.ui.main.location.list

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.app.ui.components.ErrorScreen
import com.uvg.app.ui.components.LoadingScreen
import com.uvg.app.ui.theme.AppTheme

@Composable
fun LocationListRoute(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    viewModel: LocationListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LocationListScreen(
        modifier = modifier,
        onLocationClick = onLocationClick,
        state = state,
        onLoadingClick = {
            viewModel.onLoadingClick()
        },
        onGetDataClick = {
            viewModel.onGetData()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListScreen(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    state: LocationListState,
    onLoadingClick: () -> Unit,
    onGetDataClick: () -> Unit
) {
    when {
        state.isLoading -> {
            LoadingScreen(
                modifier = modifier,
                onLoadingClick = onLoadingClick
            )

            onGetDataClick()
        }

        state.hasError -> {
            ErrorScreen(
                modifier = modifier,
                onGetData = onGetDataClick,
                errorText = "Error al obtener el listado de ubicaciones."
            )
        }

        else -> {
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
                    items(state.data) { item ->
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
                onLocationClick = {},
                state = LocationListState(
                    data = LocationDb().getAllLocations()
                ),
                onLoadingClick = {},
                onGetDataClick = {}
            )
        }
    }
}