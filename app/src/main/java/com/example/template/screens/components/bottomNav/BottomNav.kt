package com.example.template.screens.components.bottomNav

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedIndex by remember {
        mutableStateOf(2)
    }
    val items = listOf(
        BottomNavItem.CarHistory,
        BottomNavItem.TrackerCar,
        BottomNavItem.Home,
        BottomNavItem.Missing,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == null || currentRoute == Screen.LoginScreen.route|| currentRoute ==Screen.OtpScreen.route) {
        return
    } else {

        NavigationBar(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon((item.icon), contentDescription = item.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                        indicatorColor = MaterialTheme.colorScheme.surface
                    ),
                    alwaysShowLabel = false,
                    selected = currentRoute == item.screen,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(item.screen) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

}