package com.example.xhvy.data.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.entities.WorkoutExerciseWithPrevious


data class WorkoutExercise(
    val id: Int = -1,
    val exercise: Exercise,
    var completed: Boolean = false,
    var exerciseSets: SnapshotStateList<ExerciseSet> = mutableStateListOf(),
    var previousWorkoutExercise: WorkoutExercise? = null
) {
    fun getSets(): List<ExerciseSet> {
        return exerciseSets
    }

    companion object {
        fun from(workoutExerciseEntity: WorkoutExerciseFull): WorkoutExercise {
            return WorkoutExercise(
                id = workoutExerciseEntity.workoutExerciseEntity.id,
                exercise = Exercise.from(workoutExerciseEntity.exercise),
                exerciseSets = workoutExerciseEntity.exerciseSets.map { ExerciseSet.from(it) }
                    .toMutableStateList()
            )
        }

    }
}



