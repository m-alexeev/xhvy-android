package com.example.xhvy.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.ui.screens.Dashboard
import com.example.xhvy.ui.screens.ExerciseItemScreen



enum class BottomNavigation(val label: String, val icon: ImageVector, val route: MainStack) {
    DASHBOARD("Home", Icons.Filled.Home, MainStack.DashboardRoute),
    EXERCISES("Search", Icons.Filled.Info, MainStack.ExercisesRoute),
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavigation.entries.forEachIndexed { index, navigationItem ->
                    //To compare with the bottomBar route
                    val isSelected by remember(currentRoute) {
                        derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        label = { Text(navigationItem.label) },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route)
                        }
                    )
                }
            }

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = MainStack.DashboardRoute
        ) {
            composable<MainStack.DashboardRoute> {
                Dashboard(modifier = Modifier)
            }
            composable<MainStack.ExercisesRoute> {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExerciseItemScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}