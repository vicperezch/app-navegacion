package com.uvg.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.app.ui.main.character.CharacterNavGraph
import com.uvg.app.ui.main.character.list.CharacterListDestination
import com.uvg.app.ui.main.location.LocationNavGraph
import com.uvg.app.ui.main.location.list.LocationListDestination
import com.uvg.app.ui.main.userProfile.UserProfileDestination

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any
)

val navigationItems = listOf(
    NavigationItem(
        title = "Characters",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face,
        destination = CharacterNavGraph
    ),
    NavigationItem(
        title = "Locations",
        selectedIcon = Icons.Filled.LocationOn,
        unselectedIcon = Icons.Outlined.LocationOn,
        destination = LocationNavGraph
    ),
    NavigationItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = UserProfileDestination
    )
)

val topLevelDestinations = listOf(
    CharacterListDestination::class,
    LocationListDestination::class,
    UserProfileDestination::class
)