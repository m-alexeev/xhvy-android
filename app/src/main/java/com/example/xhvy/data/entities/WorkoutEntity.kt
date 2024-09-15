package com.example.xhvy.data.entities

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import java.time.Instant
import java.util.Date


@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val startTime: Date = Date.from(Instant.now()),
    var endTime: Date = Date.from(Instant.now()),
    val active: Boolean = false,
    val isTemplate: Boolean = false,
) {
    companion object {
        fun from(workout: Workout): WorkoutEntity {
            return WorkoutEntity(
                name = workout.name,
                startTime = workout.startTime,
                endTime = workout.endTime,
                active = workout.active
            )
        }

        fun from(template: Template): WorkoutEntity {
            return WorkoutEntity(
                name = template.name,
                isTemplate = true
            )
        }
    }

    fun toWorkout(fullWorkout: FullWorkout): Workout {
        val workoutExercises: List<WorkoutExercise> = getExerciseList(fullWorkout.workoutExercises)
        return Workout(
            id,
            name,
            startTime,
            endTime,
            workoutExercises = workoutExercises.toMutableList(),
            active
        )
    }

    fun toTemplate(fullTemplate: FullTemplate): Template {
        val templateExercises: List<WorkoutExercise> =
            getExerciseList(fullTemplate.workoutExercises)

        return Template(
            id = id,
            name = name,
            templateExercises = templateExercises.toMutableList(),
            active = active,
            isTemplate = true,
        )
    }

    private fun getExerciseList(workoutExercises: List<WorkoutExerciseFull>): List<WorkoutExercise> {
        return workoutExercises.map { workoutExerciseFull ->
            WorkoutExercise(
                id = workoutExerciseFull.workoutExerciseEntity.id,
                workoutExerciseFull.exercise.toExercise(),
                completed = workoutExerciseFull.workoutExerciseEntity.completed,
                exerciseSets = mutableStateListOf(*workoutExerciseFull.exerciseSets.map { set -> set.toExerciseSet() }
                    .toTypedArray()),
            )
        }
    }
}


data class FullTemplate(
    @Embedded
    val template: WorkoutEntity,

    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val workoutExercises: List<WorkoutExerciseFull>
)

data class FullWorkout(
    @Embedded
    val workout: WorkoutEntity,

    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutId",
    )
    val workoutExercises: List<WorkoutExerciseFull>
)
