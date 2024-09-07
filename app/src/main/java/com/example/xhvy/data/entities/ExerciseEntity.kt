package com.example.xhvy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: ExerciseCategory,
    val bodyPart: ExerciseBodyPart,
    @ColumnInfo(
        typeAffinity = ColumnInfo.INTEGER,
        defaultValue = "0"
    ) val deletable: Boolean = false,
) {
    companion object {
        fun from(exercise: Exercise): ExerciseEntity {
            return ExerciseEntity(
                exercise.id,
                exercise.name,
                exercise.category,
                exercise.bodyPart,
                exercise.deletable
            )
        }
    }

    fun toExercise(): Exercise {
        return Exercise(id, name, category, bodyPart, deletable)
    }
}