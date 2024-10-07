package com.uvg.app.ui.main.location.details

import Location
import LocationDb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.app.R
import com.uvg.app.ui.components.ErrorScreen
import com.uvg.app.ui.components.LoadingScreen
import com.uvg.app.ui.theme.AppTheme

@Composable
fun LocationDetailsRoute(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: LocationDetailsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LocationDetailsScreen(
        modifier = modifier,
        onNavigateBack = onNavigateBack,
        state = state,
        onLoadingClick = {
            viewModel.onLoadingClick()
        },
        onGetData = {
            viewModel.onGetData()
        }
    )
}

@Composable
private fun LocationDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onLoadingClick: () -> Unit,
    onGetData: () -> Unit,
    state: LocationDetailsState
) {
    when  {
        state.isLoading -> {
            LoadingScreen(
                modifier = modifier,
                onLoadingClick = onLoadingClick
            )

            onGetData()
        }

        state.hasError -> {
            ErrorScreen(
                modifier = modifier,
                onGetData = onGetData,
                errorText = "Error al obtener la ubicación."
            )
        }

        else -> {
            LocationDetailsContent(
                location = state.data,
                onNavigateBack = onNavigateBack,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationTopBar(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Location Details")
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun LocationDetailsContent(
    location: Location,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocationTopBar(onNavigateBack = onNavigateBack)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = location.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "ID:",
                value = location.id.toString()
            )

            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "Type:",
                value = location.type
            )

            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "Dimensions:",
                value = location.dimension
            )
        }
    }
}

@Composable
private fun InformationRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(text = value)
    }
}

@Composable
@Preview
private fun PreviewCharacterProfile() {
    AppTheme {
        Surface {
            LocationDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateBack = {},
                state = LocationDetailsState(
                    data = LocationDb().getLocationById(1),
                    hasError = true,
                    isLoading = false
                ),
                onLoadingClick = {},
                onGetData = {}
            )
        }
    }
}