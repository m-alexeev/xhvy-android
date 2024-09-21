package com.example.xhvy.domain.utils

import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.WorkoutExercise
import java.util.Date
import java.util.Locale

fun calcTimeDifference(time1: Date, time2: Date): String {
    val diff = time1.toInstant().toEpochMilli() - time2.toInstant().toEpochMilli()

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60

    if (hours > 0) {
        return "${hours}h:${minutes}m"
    }
    return "${minutes}m"
}

fun differenceResult(time: Long): String {
    var x: Long = time / 1000

    val seconds = x % 60
    x /= 60
    val minutes = x % 60
    x /= 60
    val hours = (x % 24).toInt()
    x /= 24
    val days = x.toInt()

    if (hours == 0){
        return String.format(Locale.CANADA,"%02d:%02d", minutes, seconds)
    }
    if (days == 0) {
        return String.format(Locale.CANADA,"%02d:%02d:%02d",hours, minutes, seconds)
    }
    return String.format(Locale.CANADA,format = "%02d%02d:%02d:%02d",days, hours, minutes, seconds)
}

fun calcTotalWeight(workoutExercise: List<WorkoutExercise>): Long {
    val exerciseSets: List<ExerciseSet> = workoutExercise.flatMap { it.exerciseSets }
    val total = exerciseSets.sumOf { set ->
        val reps = set.reps?.toLong() ?: 0
        val weight = set.weight?.toLong() ?: 0
        reps * weight
    }
    return total
}
