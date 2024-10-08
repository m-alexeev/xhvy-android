package com.example.xhvy.data.models

import com.example.xhvy.data.entities.ExerciseEntity
import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

sealed class ExerciseAction() {
    data object DeleteExercise : ExerciseAction()
}

enum class ExerciseCategory(private val displayName: String) {
    BARBELL("Barbell"),
    DUMBBELL("Dumbbell"),
    MACHINE("Machine"),
    CABLE("Cable"),
    WEIGHTED_BODYWEIGHT("Weighted Bodyweight"),
    ASSISTED_BODYWEIGHT("Assisted Bodyweight"),
    REPS("Reps"),
    CARDIO("Cardio"),
    DURATION("Duration");

    override fun toString(): String {
        return displayName
    }
}

@OptIn(ExperimentalStdlibApi::class)
val exerciseCategories: EnumEntries<ExerciseCategory> = enumEntries<ExerciseCategory>()

enum class ExerciseBodyPart(private val displayName: String) {
    CORE("Core"),
    ARMS("Arms"),
    BACK("Back"),
    CHEST("Chest"),
    LEGS("Legs"),
    SHOULDERS("Shoulders"),
    OTHER("Other"),
    OLYMPIC("Olympic"),
    FULL_BODY("Full Body"),
    CARDIO("Cardio");

    override fun toString(): String {
        return displayName
    }
}

@OptIn(ExperimentalStdlibApi::class)
val exerciseBodyParts: EnumEntries<ExerciseBodyPart> = enumEntries<ExerciseBodyPart>()

open class Exercise(
    val id: Int = 0,
    val name: String,
    val category: ExerciseCategory,
    val bodyPart: ExerciseBodyPart,
    val deletable: Boolean = false
) {
    companion object {
        fun from(exerciseEntity: ExerciseEntity): Exercise {
            return Exercise(
                id = exerciseEntity.id,
                name = exerciseEntity.name,
                category = exerciseEntity.category,
                bodyPart = exerciseEntity.bodyPart,
                deletable = exerciseEntity.deletable
            )
        }
    }
}

