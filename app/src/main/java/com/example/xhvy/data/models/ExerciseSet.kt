package com.example.xhvy.data.models

sealed class SetAction {
    data object AddSet : SetAction()
    data class RemoveSet(val exerciseIndex: Int, val setIndex: Int) : SetAction()
    data class UpdateSet(val setIndex: Int, val exerciseSet: ExerciseSet) : SetAction()
    data class ToggleComplete(val setIndex: Int) : SetAction()
}

data class ExerciseSet(
    val id: Int = 0,
    var reps: Int? = 0,
    var weight: Float? = 0.0f,
    var completed: Boolean = false,
)