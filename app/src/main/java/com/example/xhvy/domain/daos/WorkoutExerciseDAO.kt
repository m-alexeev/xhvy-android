package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xhvy.data.entities.WorkoutExerciseFull
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercise(workoutExerciseFull: WorkoutExerciseFull)

    @Query("Select * from `workout-exercises`")
    fun getWorkoutExercises(): Flow<List<WorkoutExerciseFull>>


}