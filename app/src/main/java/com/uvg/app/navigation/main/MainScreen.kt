package com.uvg.app.navigation.main

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
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.app.navigation.NavigationItem
import com.uvg.app.ui.character.CharacterNavGraph
import com.uvg.app.ui.character.characterGraph
import com.uvg.app.ui.character.list.CharacterListDestination
import com.uvg.app.ui.location.list.LocationListDestination
import com.uvg.app.ui.location.locationGraph
import com.uvg.app.ui.userProfile.UserProfileDestination
import com.uvg.app.ui.userProfile.userProfileScreen

@Composable
fun MainScreen(
    onLogOutClick: () -> Unit
) {
    val navController = rememberNavController()
    val menuItems = listOf(
        NavigationItem(
            title = "Characters",
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            destination = CharacterListDestination
        ),
        NavigationItem(
            title = "Locations",
            unselectedIcon = Icons.Outlined.Place,
            selectedIcon = Icons.Filled.Place,
            destination = LocationListDestination
        ),
        NavigationItem(
            title = "Profile",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle,
            destination = UserProfileDestination
        )
    )

    Scaffold(
        bottomBar = {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination

            NavigationBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                menuItems.forEach { navigationItem ->

                    val selected = currentDestination?.hierarchy?.any {
                        it.hasRoute(navigationItem.destination::class)
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(navigationItem.destination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            BadgedBox(badge = {}) {
                                Icon(
                                    imageVector = if (selected) {
                                        navigationItem.selectedIcon
                                    } else navigationItem.unselectedIcon,
                                    contentDescription = navigationItem.title,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharacterNavGraph,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            characterGraph(navController)

            locationGraph(navController)

            userProfileScreen(
                onLogoutClick = onLogOutClick
            )
        }
    }
}
