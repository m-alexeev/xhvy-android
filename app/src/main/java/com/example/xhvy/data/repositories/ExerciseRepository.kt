package com.example.xhvy.data.repositories

import com.example.xhvy.data.models.Exercise
import com.example.xhvy.domain.daos.ExerciseDAO
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDAO.insertExercise(exercise)
    }

    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDAO.getExercises()
    }
}