package com.example.xhvy.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise

@Entity(
    tableName = "workout-exercises", foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = WorkoutEntity::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE,
        )]
)
data class WorkoutExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val completed: Boolean = false,
    val exerciseId: Int,
    var workoutId: Int,

    ) {
    companion object {
        fun from(workoutExercise: WorkoutExercise): WorkoutExerciseEntity {
            return WorkoutExerciseEntity(
                completed = workoutExercise.completed,
                exerciseId = workoutExercise.exercise.id,
                workoutId = 0
            )
        }

    }

    fun toWorkoutExercise(): WorkoutExercise? {
        return null
    }
}


data class WorkoutExerciseFull(
    @Embedded val workoutExerciseEntity: WorkoutExerciseEntity,

    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id",
    )
    val exercise: ExerciseEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutExerciseId",
    )
    val exerciseSets: List<ExerciseSetEntity>
)

