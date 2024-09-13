package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.FullWorkout
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.domain.daos.WorkoutDAO
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val workoutDAO: WorkoutDAO) {


    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExerciseEntity) {
        workoutDAO.insertWorkoutExercise(workoutExercise)
    }

    suspend fun insertWorkoutExercises(workoutExercises: List<WorkoutExerciseEntity>) {
        workoutDAO.insertWorkoutExercises(workoutExercises)
    }

    suspend fun insertExerciseSet(exerciseSetEntity: ExerciseSetEntity) {
        workoutDAO.insertWorkoutExerciseSet(exerciseSetEntity)
    }

    suspend fun insertExerciseSets(exerciseSetEntities: List<ExerciseSetEntity>) {
        workoutDAO.insertExerciseSets(exerciseSetEntities)
    }


    fun deleteActiveWorkout() {
        workoutDAO.deleteActiveWorkout()
    }

    fun deleteSetById(setId: Int) {
        workoutDAO.deleteSetById(setId)
    }

    fun updateSetById(setId: Int, reps: Int?, weight: Float?, completed: Boolean) {
        workoutDAO.updateSetById(setId, reps, weight, completed)
    }

    fun updateSetById(exerciseSetEntity: ExerciseSetEntity) {
        workoutDAO.updateSetById(exerciseSetEntity)
    }

    fun deleteWorkoutExercise(workoutExerciseFull: WorkoutExerciseFull) {
    }

    suspend fun insertWorkout(workoutEntity: WorkoutEntity): Workout {
        val workoutId = workoutDAO.insertWorkout(workoutEntity)
        return workoutEntity.copy(id = workoutId.toInt())
            .toWorkout(FullWorkout(workout = workoutEntity, workoutExercises = emptyList()))
    }

    suspend fun insertWorkout(workout: Workout, workoutExercises: List<WorkoutExercise>) {
        workoutDAO.insertWorkoutTransaction(workout, workoutExercises = workoutExercises)
    }

    suspend fun completeWorkout(workoutId: Int) {
        workoutDAO.completeWorkout(workoutId)
    }

    fun getAllWorkoutExercises(): Flow<List<WorkoutExerciseFull>> {
        return workoutDAO.getWorkoutExercises()
    }

    fun getActiveWorkout(): Flow<FullWorkout?> {
        return workoutDAO.getWorkout()
    }

    fun getCompletedWorkouts(): Flow<List<FullWorkout>> {
        return workoutDAO.getAllWorkouts()
    }
}