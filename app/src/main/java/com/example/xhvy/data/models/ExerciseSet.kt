package com.example.xhvy.data.models

data class ExerciseSet(
    val id: Int = 0,
    var reps: Int = 0,
    var weight: Float = 0.0f,
    var completed: Boolean = false,
)