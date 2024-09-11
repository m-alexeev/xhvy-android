package com.example.xhvy.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.xhvy.data.models.ExerciseSet

@Entity(
    tableName = "exercise-sets", foreignKeys = [ForeignKey(
        entity = WorkoutExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["workoutExerciseId"],
        onDelete = ForeignKey.CASCADE,
    )]
)
data class ExerciseSetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var reps: Int? = 0,
    var weight: Float? = 0.0f,
    val completed: Boolean = false,
    var workoutExerciseId: Int = 0,
) {
    companion object {
        fun from(exerciseSet: ExerciseSet): ExerciseSetEntity {
            return ExerciseSetEntity(
                id = exerciseSet.id,
                reps = exerciseSet.reps,
                weight = exerciseSet.weight,
                completed = exerciseSet.completed,
            )
        }
    }

    fun toExerciseSet(): ExerciseSet {
        return ExerciseSet(id, reps, weight, completed)
    }
}