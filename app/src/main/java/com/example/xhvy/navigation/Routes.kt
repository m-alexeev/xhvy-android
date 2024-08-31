package com.example.xhvy.navigation

import kotlinx.serialization.Serializable


sealed class MainStack() {
    @Serializable
    data object ExercisesRoute : MainStack()

    @Serializable
    data object DashboardRoute : MainStack()

    @Serializable
    data object WorkoutRoute : MainStack()
}

