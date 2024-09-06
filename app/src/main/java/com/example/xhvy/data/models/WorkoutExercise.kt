package com.example.xhvy.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "workout-exercises", foreignKeys = [ForeignKey(
        entity = Exercise::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE,
    )]
)
data class WorkoutExercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val completed: Boolean = false,
    val exerciseId: Int,
) {

//    fun isCompleted(): Boolean {
//        return completed
//    }
//
//    private fun completeExercise() {
//        completed = true
//    }
//
//    fun getSets(): List<ExerciseSet> {
//        return exerciseSets
//    }
//
//    fun removeSet(set: ExerciseSet) {
//        assert(exerciseSets.contains(set))
//        exerciseSets.remove(set)
//    }
//
//    fun addSet() {
//        // Create new set based on previous set or initialize to 0
//        // Completion is false by default
//        val newSet = ExerciseSet(exerciseSets.size, 0, 0.0f)
//        if (exerciseSets.size > 0) {
//            val lastSet: ExerciseSet = exerciseSets[exerciseSets.lastIndex]
//            newSet.weight = lastSet.weight
//            newSet.repetitions = lastSet.repetitions
//        }
//        exerciseSets.add(newSet)
//    }
//
//    fun editSet(setId: Int, setData: ExerciseSet) {
//        assert(exerciseSets.find { it.id == setId } != null)
//        val item = exerciseSets.find { it.id == setId }
//        if (item != null) {
//            item.weight = setData.weight
//            item.repetitions = setData.repetitions
//        }
//    }
//
//    fun setSetCompletion(setId: Int, completed: Boolean) {
//        assert(exerciseSets.find { it.id == setId } != null)
//        val item = exerciseSets.find { it.id == setId }
//        if (item != null) {
//            item.completed = completed
//        }
//
//        // Check all sets mark exercise as complete / incomplete if all sets are completed
//        var exerciseComplete = true
//        for (it in exerciseSets) {
//            if (!it.completed) {
//                exerciseComplete = false
//                break
//            }
//        }
//        this.completed = exerciseComplete
//    }

}


data class WorkoutExerciseFull(
    @Embedded val workoutExercise: WorkoutExercise,

    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id",
    )
    val exercise: Exercise,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutExerciseId",
    )
    val exerciseSets: List<ExerciseSet>
)

data class WorkoutExerciseWithSet(
    @Embedded val workoutExercise: WorkoutExercise,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutExerciseId"
    )
    val exerciseSets: List<ExerciseSet>
)