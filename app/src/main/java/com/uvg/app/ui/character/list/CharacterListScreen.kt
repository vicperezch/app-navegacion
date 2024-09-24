package com.uvg.app.ui.character.list

import com.uvg.app.data.CharacterDb
import com.uvg.app.data.Character
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun CharacterListRoute(
    modifier: Modifier = Modifier,
    onCharacterClick: (Int) -> Unit
) {
    CharacterListScreen(
        modifier = modifier,
        onCharacterClick = onCharacterClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterListScreen(
    modifier: Modifier = Modifier,
    onCharacterClick: (Int) -> Unit
) {
    val characterDb = CharacterDb()

    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(text = "Characters",)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        LazyColumn(verticalArrangement = Arrangement.Center) {
            items(characterDb.getAllCharacters()) {character ->
                CharacterItem(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable { onCharacterClick(character.id) },
                    character = character
                )
            }
        }
    }
}

@Composable
private fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .width(75.dp)
                .height(75.dp)
        )

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W600
            )

            Text(
                text = character.species + " - " + character.status,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
private fun CharacterListPreview() {
    AppTheme {
        Surface {
            CharacterListScreen(
                modifier = Modifier.fillMaxSize(),
                onCharacterClick = {}
            )
        }
    }
}