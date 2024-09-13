package com.example.xhvy.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xhvy.data.entities.FullTemplate
import com.example.xhvy.data.entities.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TemplateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplate(template: WorkoutEntity)

    @Query("SELECT * FROM workouts where isTemplate=1 ORDER BY startTime DESC")
    suspend fun getTemplates(): Flow<List<FullTemplate>>
}