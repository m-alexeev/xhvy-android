package com.example.xhvy.navigation

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
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.screens.DashboardScreen
import com.example.xhvy.ui.screens.ExerciseItemScreen
import com.example.xhvy.ui.screens.HistoryScreen
import com.example.xhvy.ui.screens.MeasureScreen
import com.example.xhvy.ui.screens.NewWorkoutScreen
import com.example.xhvy.ui.screens.WorkoutScreen


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
                DashboardScreen(modifier = Modifier)
            }
            composable<MainStack.HistoryRoute> {
                HistoryScreen()
            }
            navigation<MainStack.WorkoutRoute>(startDestination = WorkoutStack.AllWorkouts) {
                composable<WorkoutStack.AllWorkouts> {
                    WorkoutScreen(navController)
                }
                composable<WorkoutStack.NewWorkout> {
                    NewWorkoutScreen()
                }
            }
            navigation<MainStack.ExercisesRoute>(startDestination = ExerciseStack.AllExercises) {
                composable<ExerciseStack.AllExercises> {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        ExerciseItemScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
                composable<ExerciseStack.NewExercise> {
                    Text(text = "New Exercise")
                }
                composable<ExerciseStack.EditExercise> {
                    Text(text = "Edit Exercise")
                }
            }
            composable<MainStack.MeasurementsRoute> {
                MeasureScreen()
            }
        }
    }

}