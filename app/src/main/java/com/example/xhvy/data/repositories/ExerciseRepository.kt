package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.domain.daos.ExerciseDAO
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    suspend fun insertExercise(exercise: ExerciseEntity) {
        exerciseDAO.insertExercise(exercise)
    }

    fun deleteExercise(exercise: ExerciseEntity) {
        exerciseDAO.delete(exercise)
    }

    fun getAllExercises(): Flow<List<ExerciseEntity>> {
        return exerciseDAO.getExercises()
    }
}