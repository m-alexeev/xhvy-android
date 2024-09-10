package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.FullWorkout
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.util.Date

@Dao
interface WorkoutDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercise(workoutExerciseEntity: WorkoutExerciseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercises(workoutExerciseEntities: List<WorkoutExerciseEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExerciseSet(exerciseSetEntity: ExerciseSetEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseSets(exerciseSets: List<ExerciseSetEntity>): List<Long>

    @Transaction
    @Query("Select * from `workouts` ORDER BY `startTime` DESC")
    fun getAllWorkouts(): Flow<List<FullWorkout>>

    @Transaction
    suspend fun insertWorkoutTransaction(
        workout: Workout,
        workoutExercises: List<WorkoutExercise>
    ) {
        val workoutWithEnd = WorkoutEntity.from(workout)
        workoutWithEnd.endTime = Date.from(Instant.now())
        val workoutId = insertWorkout(workoutWithEnd)

        workoutExercises.forEach { workoutExercise ->
            // Insert workout exercises
            val workoutExerciseEntity = WorkoutExerciseEntity.from(workoutExercise)
            workoutExerciseEntity.workoutId = workoutId.toInt()
            val workoutExerciseId = insertWorkoutExercise(
                workoutExerciseEntity
            )
            // Insert Exercise Sets
            val exerciseSets: List<ExerciseSetEntity> = workoutExercise.exerciseSets.map { set ->
                val exerciseSetEntity = ExerciseSetEntity.from(set)
                exerciseSetEntity.workoutExerciseId = workoutExerciseId.toInt()
                exerciseSetEntity
            }

            insertExerciseSets(exerciseSets)
        }
    }

    @Query("Select * from `workout-exercises`")
    fun getWorkoutExercises(): Flow<List<WorkoutExerciseFull>>


}