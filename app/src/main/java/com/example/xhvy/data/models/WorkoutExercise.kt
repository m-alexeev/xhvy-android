package com.example.xhvy.data.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList


data class WorkoutExercise(
    val id: Int = -1,
    val exercise: Exercise,
    var completed: Boolean = false,
    var exerciseSets: SnapshotStateList<ExerciseSet> = mutableStateListOf()
) {
    fun getSets(): List<ExerciseSet> {
        return exerciseSets
    }

}


