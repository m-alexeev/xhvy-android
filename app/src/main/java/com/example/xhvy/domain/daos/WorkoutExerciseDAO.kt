package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.xhvy.data.models.WorkoutExercise

@Dao
interface WorkoutExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise)

}