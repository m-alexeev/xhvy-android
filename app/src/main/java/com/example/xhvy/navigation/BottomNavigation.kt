package com.example.xhvy.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.screens.Dashboard
import com.example.xhvy.ui.screens.ExerciseItemScreen
import com.example.xhvy.ui.screens.HistoryScreen
import com.example.xhvy.ui.screens.MeasureScreen


@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar {
                BottomNavigation.entries.forEachIndexed { _, item ->
                    val isSelected by remember(currentRoute) {
                        derivedStateOf { currentRoute == item.route::class.qualifiedName }
                    }
                    BottomNavBarItem(
                        selected = isSelected,
                        onClick = { navController.navigate(item.route) },
                        icon = { color ->
                            FaIcon(
                                iconPainterId = item.iconResId,
                                contentDescription = null,
                                tint = color
                            )
                        },
                        label = { color ->
                            Text(
                                item.label,
                                style = MaterialTheme.typography.titleSmall,
                                color = color
                            )
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
            composable<MainStack.HistoryRoute>{
                HistoryScreen()
            }
            composable<MainStack.WorkoutRoute> {
                Column {
                    Text(text = "Workouts")
                }
            }
            composable<MainStack.ExercisesRoute> {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExerciseItemScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
            composable<MainStack.MeasurementsRoute> {
                MeasureScreen()
            }
        }
    }

}