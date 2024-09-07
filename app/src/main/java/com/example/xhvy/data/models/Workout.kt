package com.example.xhvy.data.models

import java.time.Instant
import java.util.Date

data class Workout(
    val id: Int = 0,
    val name: String,
    val startTime: Date = Date.from(Instant.now()),
    val endTime: Date? = null,
    val workoutExercises: MutableList<WorkoutExercise> = mutableListOf()
) {

}