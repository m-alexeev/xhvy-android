package com.example.xhvy.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

enum class ExerciseCategory(private val displayName: String) {
    BARBELL("Barbell"),
    DUMBBELL("Dumbbell"),
    MACHINE("Machine"),
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

@Entity(tableName = "exercises")
open class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: ExerciseCategory,
    val bodyPart: ExerciseBodyPart,
    @ColumnInfo(
        typeAffinity = ColumnInfo.INTEGER,
        defaultValue = "0"
    ) val deletable: Boolean = false,
) {


    override fun equals(other: Any?): Boolean {
        // if the other exercise is this exercise
        if (this === other) return true

        // If not exercise then not equal
        if (other !is Exercise) return false

        // exercises are equal if their ids are equal
        return id == other.id
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + bodyPart.hashCode()
        return result
    }
}

