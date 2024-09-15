package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.FullTemplate
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.models.WorkoutExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface TemplateDAO {
    @Query("SELECT * FROM workouts where isTemplate=1 ORDER BY startTime DESC")
    fun getTemplates(): Flow<List<FullTemplate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplate(workout: WorkoutEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercise(workoutExerciseEntity: WorkoutExerciseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseSets(exerciseSets: List<ExerciseSetEntity>): List<Long>

    @Query("DELETE FROM workouts where id=:templateId")
    suspend fun deleteTemplateById(templateId: Int)

    @Transaction
    suspend fun insertTemplateTransaction(
        template: Template,
        templateExercises: List<WorkoutExercise>
    ) {
        val templateEntity = WorkoutEntity.from(template)
        val templateId = insertTemplate(templateEntity)

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
}