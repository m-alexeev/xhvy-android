package com.example.xhvy.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.xhvy.data.models.Workout
import java.time.Instant
import java.util.Date


@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val startTime: Date = Date.from(Instant.now()),
    val endTime: Date? = null
) {
    companion object {
        fun from(workout: Workout): WorkoutEntity {
            return WorkoutEntity(
                name = workout.name,
                startTime = workout.startTime,
                endTime = workout.endTime
            )
        }
    }
}


data class FullWorkout(
    @Embedded
    val workout: WorkoutEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId",
    )
    var workoutExercises: List<WorkoutExerciseFull>
)
