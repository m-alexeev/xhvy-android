package com.example.xhvy.data.models

enum class ExerciseCategory {
    BARBELL,
    DUMBBELL,
    MACHINE,
    WEIGHTED_BODYWEIGHT,
    ASSISTED_BODYWEIGHT,
    REPS,
    CARDIO,
    DURATION,
}


enum class ExerciseBodyType{
    NONE,
    CORE,
    ARMS,
    BACK,
    CHEST,
    LEGS,
    SHOULDERS,
    OTHER,
    OLYMPIC,
    FULL_BODY,
    CARDIO,
}


open class Exercise(
    val id: Int,
    val name: String,
    val category: ExerciseCategory,
    val bodyType: ExerciseBodyType,
){


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
        result = 31 * result + bodyType.hashCode()
        return result
    }
}

