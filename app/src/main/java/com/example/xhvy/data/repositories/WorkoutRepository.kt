package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.FullWorkout
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseWithPrevious
import com.example.xhvy.data.models.Workout
import com.example.xhvy.domain.daos.WorkoutDAO
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val workoutDAO: WorkoutDAO) {

    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExerciseEntity) {
        val exerciseId = workoutDAO.insertWorkoutExercise(workoutExercise)
        workoutDAO.insertExerciseSets(listOf(ExerciseSetEntity(workoutExerciseId = exerciseId.toInt())))
    }

    fun getWorkout(id: Int): Flow<FullWorkout> {
        return workoutDAO.getWorkout(id)
    }

    fun workoutExerciseWithPrevious(): Flow<WorkoutExerciseWithPrevious> {
        return workoutDAO.getWorkoutWithHistoric()
    }

    suspend fun insertExerciseSet(exerciseSetEntity: ExerciseSetEntity) {
        workoutDAO.insertWorkoutExerciseSet(exerciseSetEntity)
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


    suspend fun deleteWorkout(workoutId: Int) {
        workoutDAO.deleteWorkout(workoutId)
    }

    suspend fun workoutArchiveToggle(workoutId: Int, archive: Boolean) {
        workoutDAO.toggleArchive(workoutId, archive)
    }

//    fun updateSetById(exerciseSetEntity: ExerciseSetEntity) {
//        workoutDAO.updateSetById(exerciseSetEntity)
//    }


    suspend fun insertWorkout(workoutEntity: WorkoutEntity): Workout {
        val workoutId = workoutDAO.insertWorkout(workoutEntity)
        return workoutEntity.copy(id = workoutId.toInt())
            .toWorkout(FullWorkout(workout = workoutEntity, workoutExercises = emptyList()))
    }


    suspend fun completeWorkout(workoutId: Int) {
        workoutDAO.completeWorkout(workoutId)
    }

    fun getActiveWorkout(): Flow<FullWorkout?> {
        return workoutDAO.getWorkoutList()
    }

    fun getCompletedWorkouts(): Flow<List<FullWorkout>> {
        return workoutDAO.getAllWorkouts()
    }
}