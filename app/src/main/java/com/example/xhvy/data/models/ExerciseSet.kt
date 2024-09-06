package com.example.xhvy.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise-sets", foreignKeys = [ForeignKey(
        entity = WorkoutExercise::class,
        parentColumns = ["id"],
        childColumns = ["workoutExerciseId"],
        onDelete = ForeignKey.CASCADE,
    )]
)
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val reps: Int = 0,
    val weight: Float = 0.0f,
    val completed: Boolean = false,
    val workoutExerciseId: Int,
)