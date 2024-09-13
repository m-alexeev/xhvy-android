package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.data.models.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises")
    fun getExercises(): Flow<List<ExerciseEntity>>

    @Delete
    fun delete(exercise: ExerciseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listOf: List<ExerciseEntity>)
}