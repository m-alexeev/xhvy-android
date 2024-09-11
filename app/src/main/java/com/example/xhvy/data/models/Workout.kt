package com.example.xhvy.data.models

import java.time.Instant
import java.util.Date

sealed class WorkoutAction() {
    data object CompleteWorkout : WorkoutAction();
    data object AddExercise : WorkoutAction();
    data class AddSet(val exerciseId: Int) : WorkoutAction();
    data class RemoveSet(val setId: Int) : WorkoutAction();
    data class EditSet(val exerciseSet: ExerciseSet) : WorkoutAction()
    data object CancelWorkout : WorkoutAction()
}

data class Workout(
    val id: Int = 0,
    val name: String,
    val startTime: Date = Date.from(Instant.now()),
    val endTime: Date = Date.from(Instant.now()),
    var workoutExercises: MutableList<WorkoutExercise> = mutableListOf(),
    val active: Boolean = false,
) {

}