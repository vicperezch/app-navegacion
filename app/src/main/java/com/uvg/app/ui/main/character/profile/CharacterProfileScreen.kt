package com.uvg.app.ui.main.character.profile

import com.uvg.app.data.Character
import com.uvg.app.data.CharacterDb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.app.ui.theme.AppTheme

@Composable
fun CharacterProfileRoute(
    modifier: Modifier = Modifier,
    id: Int,
    onNavigateBack: () -> Unit
) {
    val characterDb = CharacterDb()
    CharacterProfileScreen(
        modifier = modifier,
        character = characterDb.getCharacterById(id),
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun CharacterProfileScreen(
    modifier: Modifier = Modifier,
    character: Character,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterTopBar(onNavigateBack = onNavigateBack)

        Spacer(modifier = Modifier.height(20.dp))

        AsyncImage(
            model = character.image,
            contentDescription = "com.uvg.app.data.Character image",
            modifier = Modifier
                .clip(CircleShape)
                .height(300.dp)
                .width(300.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = character.name,
            fontWeight = FontWeight.W600,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "Species:",
                value = character.species
            )

            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "Status:",
                value = character.status
            )

            InformationRow(
                modifier = Modifier.fillMaxWidth(),
                label = "Gender:",
                value = character.gender
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterTopBar(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Character Details")
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
            CharacterProfileScreen(
                modifier = Modifier.fillMaxSize(),
                character = Character(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "Human",
                    "Male",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
                onNavigateBack = {}
            )
        }
    }
}
