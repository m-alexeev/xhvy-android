package com.example.xhvy.navigation

import com.example.xhvy.R
import kotlinx.serialization.Serializable


sealed class MainStack() {
    @Serializable
    data object ExercisesRoute : MainStack()

    @Serializable
    data object HistoryRoute : MainStack()

    @Serializable
    data object DashboardRoute : MainStack()

    @Serializable
    data object WorkoutRoute : MainStack()

    @Serializable
    data object MeasurementsRoute : MainStack()
}


enum class BottomNavigation(val label: String, val iconResId: Int, val route: MainStack) {
    DASHBOARD(
        "Dashboard",
        R.drawable.ic_dashboard,
        MainStack.DashboardRoute
    ),
    HISTORY("History", R.drawable.ic_history, MainStack.HistoryRoute),
    WORKOUTS("Workouts", R.drawable.ic_plus, MainStack.WorkoutRoute),
    EXERCISES("Exercises", R.drawable.ic_dumbbell, MainStack.ExercisesRoute),
    MEASURE("Measure", R.drawable.ic_ruler, MainStack.MeasurementsRoute)
}
