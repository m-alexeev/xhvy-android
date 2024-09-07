package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.domain.daos.WorkoutExerciseDAO
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val workoutExerciseDAO: WorkoutExerciseDAO) {
    suspend fun insertWorkoutExercise(workoutExerciseFull: WorkoutExerciseFull) {
        workoutExerciseDAO.insertWorkoutExercise(workoutExerciseFull)
    }

    fun deleteWorkoutExercise(workoutExerciseFull: WorkoutExerciseFull) {
    }

    fun getAllWorkoutExercises(): Flow<List<WorkoutExerciseFull>> {
        return workoutExerciseDAO.getWorkoutExercises()
    }
}