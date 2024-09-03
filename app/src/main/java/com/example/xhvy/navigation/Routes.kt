package com.example.xhvy.navigation

import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
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

sealed class WorkoutStack(){
    @Serializable
    data object AllWorkouts: WorkoutStack()

    @Serializable
    data object NewWorkout: WorkoutStack()
}

sealed class ExerciseStack(){
    @Serializable
    data object AllExercises: ExerciseStack()

    @Serializable
    data object NewExercise: ExerciseStack()

    @Serializable
    data object EditExercise: ExerciseStack()
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


