package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.FullTemplate
import com.example.xhvy.data.entities.FullWorkout
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.models.Template
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

    @Query("DELETE from workouts where active = 1")
    fun deleteActiveWorkout()

    @Query("DELETE FROM `exercise-sets` where id =:setId")
    fun deleteSetById(setId: Int)

    @Query("UPDATE `exercise-sets` SET reps=:reps, weight=:weight, completed=:completed WHERE id=:setId")
    fun updateSetById(setId: Int, reps: Int?, weight: Float?, completed: Boolean)

    @Update
    fun updateSetById(exerciseSetEntity: ExerciseSetEntity)

    @Query("SELECT * from workouts where active = 1")
    fun getWorkout(): Flow<FullWorkout?>

    @Transaction
    @Query("Select * from `workouts` WHERE active=0 AND isTemplate=0 ORDER BY `startTime` DESC")
    fun getAllWorkouts(): Flow<List<FullWorkout>>

    @Query("UPDATE workouts set active = 0 where id =:workoutId")
    suspend fun completeWorkout(workoutId: Int)

    @Transaction
    suspend fun insertTemplateTransaction(
        template: Template,
        templateExercises: List<WorkoutExercise>
    ) {
        val templateEntity = WorkoutEntity.from(template)
        val templateId = insertWorkout(templateEntity)

        templateExercises.forEach { templateExercise ->
            val templateExerciseEntity = WorkoutExerciseEntity.from(templateExercise)
            templateExerciseEntity.workoutId = templateId.toInt()

            val templateExerciseId = insertWorkoutExercise(templateExerciseEntity)
            val exerciseSets: List<ExerciseSetEntity> = templateExercise.exerciseSets.map { set ->
                val exerciseSetEntity = ExerciseSetEntity.from(
                    set.copy(
                        reps = set.reps,
                        weight = set.weight,
                        completed = false
                    )
                )
                exerciseSetEntity.workoutExerciseId = templateExerciseId.toInt()
                exerciseSetEntity
            }

            insertExerciseSets(exerciseSets)
        }
    }

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

    @Query("Select * from workouts where isTemplate == 1")
    fun getAllTemplates(): Flow<List<FullTemplate>>

}