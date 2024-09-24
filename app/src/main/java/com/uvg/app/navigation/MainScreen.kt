package com.uvg.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.app.ui.character.list.CharacterListDestination
import com.uvg.app.ui.character.list.characterListScreen
import com.uvg.app.ui.character.list.navigateToCharacterListScreen
import com.uvg.app.ui.character.profile.CharacterProfileDestination
import com.uvg.app.ui.character.profile.characterProfileScreen
import com.uvg.app.ui.character.profile.navigateToCharacterProfileScreen
import com.uvg.app.ui.location.details.LocationDetailsDestination
import com.uvg.app.ui.location.details.locationDetailsScreen
import com.uvg.app.ui.location.details.navigateToLocationDetailsScreen
import com.uvg.app.ui.location.list.LocationListDestination
import com.uvg.app.ui.location.list.locationListScreen
import com.uvg.app.ui.location.list.navigateToLocationListScreen
import com.uvg.app.ui.login.LoginDestination
import com.uvg.app.ui.login.loginScreen
import com.uvg.app.ui.userProfile.UserProfileDestination
import com.uvg.app.ui.userProfile.userProfileScreen

@Composable
fun MainRoute() {
    MainScreen()
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val menuItems = listOf(
        NavigationItem(
            title = "Characters",
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            hasNews = false,
            badgeCount = null,
            destination = CharacterListDestination
        ),
        NavigationItem(
            title = "Locations",
            unselectedIcon = Icons.Outlined.Place,
            selectedIcon = Icons.Filled.Place,
            hasNews = false,
            badgeCount = null,
            destination = LocationListDestination
        ),
        NavigationItem(
            title = "Profile",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle,
            hasNews = false,
            badgeCount = null,
            destination = UserProfileDestination
        )
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                menuItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(navigationItem.destination)
                        },
                        icon = {
                            BadgedBox(
                                badge = {}
                            ) {
                                Icon(
                                    imageVector = if (index == selectedIndex) {
                                        navigationItem.selectedIcon
                                    } else navigationItem.unselectedIcon,
                                    contentDescription = navigationItem.title,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharacterListDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            loginScreen (
                onLoginClick = {
                    navController.navigateToCharacterListScreen(
                        destination = CharacterListDestination,
                        navOptions = navOptions {
                            popUpTo<LoginDestination>() {
                                inclusive = true
                            }
                        }
                    )
                }
            )

            characterListScreen(
                onCharacterClick = { id ->
                    navController.navigateToCharacterProfileScreen(
                        destination = CharacterProfileDestination(
                            characterId = id
                        )
                    )
                }
            )

            characterProfileScreen(
                onNavigateBack = {
                    navController.navigateToCharacterListScreen(
                        destination = CharacterListDestination,
                        navOptions = navOptions {
                            popUpTo(0)
                        }
                    )
                }
            )

            locationListScreen(
                onLocationClick = { id ->
                    navController.navigateToLocationDetailsScreen(
                        destination = LocationDetailsDestination(
                            locationId = id
                        )
                    )
                }
            )

            locationDetailsScreen(
                onNavigateBack = {
                    navController.navigateToLocationListScreen(
                        destination = LocationListDestination,
                        navOptions = navOptions {
                            popUpTo(0)
                        }
                    )
                }
            )

            userProfileScreen(
                onLogoutClick = {}
            )
        }
    }
}