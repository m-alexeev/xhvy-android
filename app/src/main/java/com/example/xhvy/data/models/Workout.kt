package com.example.xhvy.data.models

import com.example.xhvy.R
import com.example.xhvy.ui.components.general.DropdownOption
import java.time.Instant
import java.util.Date


sealed class WorkoutDropdownAction() {
    data object EditWorkout : WorkoutDropdownAction()
    data object ArchiveWorkout : WorkoutDropdownAction()
    data object DeleteWorkout : WorkoutDropdownAction()
}

sealed class WorkoutAction() {
    data object CompleteWorkout : WorkoutAction()
    data object AddExercise : WorkoutAction()
    data class AddSet(val exerciseId: Int) : WorkoutAction()
    data class RemoveSet(val setId: Int) : WorkoutAction()
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
    val archived: Boolean = false,


)

fun getWorkoutDropdownActions(): List<DropdownOption<WorkoutDropdownAction>> {
    return listOf(
        DropdownOption(
            label = "Edit",
            icon = R.drawable.ic_pencil,
            action = WorkoutDropdownAction.EditWorkout
        ),
        DropdownOption(
            label = "Archive",
            icon = R.drawable.ic_archive,
            action = WorkoutDropdownAction.ArchiveWorkout
        ),
        DropdownOption(
            label = "Delete",
            icon = R.drawable.ic_trash_2,
            action = WorkoutDropdownAction.DeleteWorkout
        )
    )
}