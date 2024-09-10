package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.domain.daos.WorkoutDAO
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val workoutDAO: WorkoutDAO) {
    suspend fun insertWorkoutExercises(workoutExercise: WorkoutExerciseEntity) {
        workoutDAO.insertWorkoutExercise(workoutExercise)
    }

//    suspend fun insertWorkout(workoutEntity: WorkoutEntity) {
//        workoutDAO.insertWorkout(workoutEntity)
//    }

    suspend fun insertWorkoutExercises(workoutExercises: List<WorkoutExerciseEntity>) {
        workoutDAO.insertWorkoutExercises(workoutExercises)
    }

    suspend fun insertExerciseSets(exerciseSetEntities: List<ExerciseSetEntity>) {
        workoutDAO.insertExerciseSets(exerciseSetEntities)
    }

    fun deleteWorkoutExercise(workoutExerciseFull: WorkoutExerciseFull) {
    }

    suspend fun insertWorkout(workout: Workout, workoutExercises: List<WorkoutExercise>) {
        workoutDAO.insertWorkoutTransaction(workout, workoutExercises = workoutExercises)
    }

    fun getAllWorkoutExercises(): Flow<List<WorkoutExerciseFull>> {
        return workoutDAO.getWorkoutExercises()
    }
}