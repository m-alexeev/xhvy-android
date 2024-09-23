package com.example.xhvy.data.models

import com.example.xhvy.data.entities.ExerciseSetEntity

sealed class SetAction {
    data object AddSet : SetAction()
    data class RemoveSet(val setId: Int) : SetAction()
    data class UpdateSet(val exerciseSet: ExerciseSet) : SetAction()
}


data class ExerciseSet(
    val id: Int = 0,
    var reps: Int? = 0,
    var weight: Float? = 0.0f,
    var completed: Boolean = false,
) {
    companion object {
        fun from(exerciseSetEntity: ExerciseSetEntity): ExerciseSet {
            return ExerciseSet(
                id = exerciseSetEntity.id,
                reps = exerciseSetEntity.reps,
                weight = exerciseSetEntity.weight,
                completed = exerciseSetEntity.completed
            )
        }
    }
}